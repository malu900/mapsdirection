import React, { Component } from "react";
import { getUserProfile, getCreatedByDirections, getFavoriteDirections } from "../../util/APIUtils";
import { toast } from "react-toastify";
import Direction from "../../direction/Direction";
import { Tabs, Tab } from 'react-bootstrap';

export default class Profile extends Component {
  constructor(props) {
    super(props);

    this.state = {
      user: null,
      directions: [],
      favoriteDirections: []
    };
  }

  loadUserProfile = (username) => {
    getUserProfile(username)
      .then((response) => {
        this.setState({
          user: response,
        });
      })
      .catch((error) => {
        if (error.status === 401) {
          toast.error("Not authorized");
        } else {
          toast.error("Something went wrong! :(");
        }
      });
  };

  getDirectionsFromCurrentUser = (username) => {
    getCreatedByDirections(username)
    .then((response) => {
      this.setState({
        directions: response
      })
    })
  }
  getDirectionsFavoritesFromCurrentUser = (username) => {
    getFavoriteDirections(username)
    .then((response) => {
      this.setState({
        favoriteDirections: response
      })
    })
  }
  createUserDirectionsHandle = (username) => {
    getCreatedByDirections(username)
    .then((response) => {
      this.setState({
        directions: response
      })
    })
  }

  componentDidMount() {
    const username = this.props.match.params.username;
    this.loadUserProfile(username);
    this.getDirectionsFromCurrentUser(username)
    this.getDirectionsFavoritesFromCurrentUser(username);
  }
  render() {
    return (
      <div className="profile">
        {this.state.user ? (
          <div className="user-profile">
            <div className="user-details">
              <div className="user-avatar"></div>
              <div className="user-summary">
                <div className="full-name">{this.state.user.name}</div>
                <div className="username">@{this.state.user.username}</div>
              </div>
            </div>
            <div>
          
          <hr/>
          <div> 
            <Tabs defaultActiveKey="home" transition={false} id="noanim-tab-example">
              <Tab eventKey="home" title="My directions">
              <div className="row"> {this.state.directions.map((direction) => (
            <Direction key={direction.id} direction={direction}/>
          ))} </div>
              </Tab>
              <Tab eventKey="profile" title="Saved directions">
              <div className="row"> {this.state.favoriteDirections.map((direction) => (
            <Direction key={direction.id} direction={direction}/>
          ))} </div>
              </Tab>
            </Tabs>
          </div>
          
            </div>
          </div>
        ) : null}
      </div>
    );
  }
}
