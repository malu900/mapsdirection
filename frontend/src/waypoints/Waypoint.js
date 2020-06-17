import React, { Component } from 'react';

export class Waypoint extends Component {

    render() {
        const { name, latitude, longitude } = this.props.waypoint;
        return (
            <div>
                <p style={{margin: '0'}}> name: {name} </p>
                <p style={{margin: '0'}}>latitude: {latitude} </p>
                <p style={{margin: '0'}}>longitude {longitude} </p>
                <hr/>
            </div>
        )
    }
}

export default Waypoint
