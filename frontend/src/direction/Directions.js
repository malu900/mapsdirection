import React, { Component } from 'react'
import { getListDirections } from '../util/APIUtils';
import { toast } from "react-toastify";
import Direction from './Direction';

export class Directions extends Component {
    constructor(props) {
        super(props);

        this.state = {
            directions: [],
            currentUser: null, 
        }
    }

    componentDidMount() {
        this.getAllDirections();
    }

    getAllDirections = () => {
        getListDirections()
          .then((response) => {
            this.setState({
              directions: response,
              currentUser: this.props.currentUser
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
    render() {
        return ( <div className="row"> {this.state.directions.map((direction) => (
            <Direction key={direction.id} direction={direction} currentUser={this.state.currentUser}/>
          ))} </div>)
    }
}

export default Directions
