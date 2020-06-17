import React, { Component } from 'react';
import { Container, Card, Button } from 'react-bootstrap';
import Waypoint from '../waypoints/Waypoint';
import { getUserProfile, createUserDirection } from "../util/APIUtils";
import { toast } from "react-toastify";
import { Redirect, Link } from 'react-router-dom';



export class Direction extends Component {
    constructor(props){
        super(props);        
        this.state = {
            id: null,
            name: '', 
            waypoints: [],
            creator: {
                id: null,
                name: '',
                username: ''
            },
            createdDate: null
        }
    }

    componentDidMount() {
        this.addToWaypoints();
    }

    addToWaypoints = () => {
        this.setState({
            id: this.props.direction.id,
            name: this.props.direction.name,
            waypoints: this.props.direction.waypoints,
            creator: {
                id: this.props.direction.createdBy.id,
                name: this.props.direction.createdBy.username,
                username: this.props.direction.createdBy.name
            },
            createdDate: this.props.direction.creationDateTime,
            redirect: false

        })

    }
    createUserDirectionsHandle = (username, id) => {
        console.log(this.props.direction)
        createUserDirection(username, id)
        .then((response) => {
          console.log('Created');
        })
        .catch((error) => {
            if(error.status === 500) {
                toast.error("Already added this")
            }
        })
      }

    loadUserProfile = (username) => {
        getUserProfile(username)
          .then((response) => {
              console.log(response)
              this.setState({
                  redirect: true
              })
              this.context.router.history.push('/users/' + response.username)
          })
          .catch((error) => {
            if (error.status === 401) {
              toast.error("Not authorized");
            } else {
              toast.error("Something went wrong! :(");
            }
          });
      };

    render() {
        return (
                <Card key={this.state.id} className="col-md-4">
                    <Card.Header> 
                        <h4>{this.state.name}</h4>
                    </Card.Header>
                    <Card.Body>  
                        <div>
                            {this.state.waypoints.map((waypoint) => (
                                <Waypoint key={waypoint.id} waypoint={waypoint} />
                            ))
                            }
                        </div>
                    </Card.Body>        
                    <Card.Footer>
                    <p>{this.state.createdDate}</p>
                    <Link to={"/users/" + this.state.creator.username} className="btn btn-primary">{this.state.creator.username}</Link>
                    </Card.Footer>         
                    <Button onClick={() => this.createUserDirectionsHandle(this.state.creator.username, this.state.id)}></Button>   
                </Card>
        )
    }
}

export default Direction
