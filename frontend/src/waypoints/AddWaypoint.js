import React, { Component } from 'react'
import { Form, Button } from 'react-bootstrap';

export class AddWaypoint extends Component {
    constructor(props) {
        super(props);

        this.state = {
            waypoint_name: '',
            latitude: '',
            longitude: ''
        }
    }
    onChange = (e) => {
      this.setState({
        [e.target.name]: e.target.value,
      });
    };

    onSubmit = (e) => {
      e.preventDefault();
      let waypoint = {
        name: this.state.waypoint_name,
        latitude: this.state.latitude,
        longitude: this.state.longitude
      }
      this.props.addWaypoint(waypoint);

      this.setState({
            waypoint_name: '',
            latitude: '',
            longitude: ''
      })
    }

    render() {
        return (
          <Form onSubmit={this.onSubmit}>
            <Form.Group controlId="formWaypoint">
              <Form.Label>Waypoint name</Form.Label>
              <Form.Control
                type="text"
                name="waypoint_name"
                placeholder="waypoint name"
                value={this.state.waypoint_name}
                onChange={this.onChange}
              />
            </Form.Group>
            <Form.Group controlId="formWaypoint"> 
            <Form.Label>Latitude</Form.Label>
            <Form.Control
                type="number"
                name="latitude"
                placeholder="latitude"
                value={this.state.latitude}
                onChange={this.onChange}
              />
            </Form.Group>
            <Form.Group controlId="formWaypoint">
              <Form.Label>Longitude</Form.Label>
              <Form.Control
                type="number"
                name="longitude"
                placeholder="longitude"
                value={this.state.longitude}
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
        )
    }
}

export default AddWaypoint
