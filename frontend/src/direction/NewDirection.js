import React, { Component } from 'react'
import { createDirection } from '../util/APIUtils';
import { toast } from "react-toastify";
import { Container, Form, Row, Button } from 'react-bootstrap';
import Waypoints from '../waypoints/Waypoints';
import AddWaypoint from '../waypoints/AddWaypoint';

export class NewDirection extends Component {
    constructor(props) {
        super(props);
        this.state = {
            name: '',
            waypoints: [],
            success: '',
        }
    }

    handleSubmit = (e) => {
        e.preventDefault();
        
        const directionData = {
          name: this.state.name,
          waypoints: this.state.waypoints
        }

        createDirection(directionData)
        .then(response => {
            this.setState({
              success: response.success
            })
            if(this.state.success) {
              toast.success(response.message)
            }
        }).catch(error => {
            if(error.status === 401) {
                this.props.handleLogout('/login', 'error', 'You have been logged out. please login to create a direction')
            } else {
                toast.error("Something went wrong! :(");
            }
        })
        this.setState({
          name: '',
            waypoints: [],
            success: '',
    })
    }

    onChange = (e) => {
      this.setState({
        name: e.target.value,
      });
    };

    addWaypoint = (waypoint) => {
      this.setState({
        waypoints: [...this.state.waypoints, waypoint]
      })
    }


    render() {
        return (
            <Container>
            <h3> Add direction </h3>
            <Row>
              <div className="col-md-6"> 
              <div className="row">
              <div className="col-md-6">
              <Form onSubmit={this.handleSubmit}>
                <Form.Group controlId="formBasicEmail">
                  <Form.Label>Direction name</Form.Label>
                  <Form.Control
                    type="text"
                    name="name"
                    placeholder="Enter name"
                    value={this.state.name}
                    onChange={this.onChange}
                  />
                </Form.Group>                
                <Button
                  variant="primary"
                  type="submit"
                >
                  Submit
                </Button>
              </Form>
              </div>
              <div className="col-md-6"> 
              <AddWaypoint addWaypoint={this.addWaypoint}/>
              </div>
              </div>
              </div>
              <div className="col-md-6"> 
              <Waypoints waypoints={this.state.waypoints}/> 
              </div> 
            </Row>
          </Container>
        )
    }
}

export default NewDirection
