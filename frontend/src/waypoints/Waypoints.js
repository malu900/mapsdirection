import React, { Component } from 'react'
import Waypoint from './Waypoint';

export class Waypoints extends Component {

    logthing() {
        this.props.waypoints.map((waypoint, index) => {
      })
      }
    componentDidUpdate() {
        this.logthing();
    }
    render() {
        return this.props.waypoints.map((waypoint, index) => (
            <Waypoint key={index} waypoint={waypoint} />
          ));
    }
}

export default Waypoints
