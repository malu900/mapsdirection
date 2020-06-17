import React, { Component } from "react";
import "./App.scss";
import "../common/AppHeader";
import AppHeader from "../common/AppHeader";
import { Container, Button } from "react-bootstrap";
import { Switch, Route, withRouter } from "react-router-dom";
import { ToastContainer, toast, Zoom, Bounce } from "react-toastify";
import Login from "../user/login/Login";
import Signup from "../user/signup/Signup";
import { getCurrentUser } from "../util/APIUtils";
import { ACCESS_TOKEN } from "../constants/index";
import Profile from "../user/profile/Profile";
import { NewDirection } from "../direction/NewDirection";
import NotFound from "../common/NotFound";
import Directions from "../direction/Directions";


export class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      currentUser: null,
      isAuthenticated: false,
    };
  }
  loadCurrentUser = () => {
    getCurrentUser().then((response) => {
      this.setState({
        currentUser: response,
        isAuthenticated: true,
      });
    });
  };

  componentDidMount() {
    this.loadCurrentUser();
  }

  handleLogout = () => {
    localStorage.removeItem(ACCESS_TOKEN);

    this.setState({
      currentUser: null,
      isAuthenticated: false,
    });

    this.props.history.push("/");
  };

  handleLogin = () => {
    this.loadCurrentUser();
    this.props.history.push("/");
  };

  render() {
    return (
      <div className="App">
        <AppHeader
          isAuthenticated={this.state.isAuthenticated}
          currentUser={this.state.currentUser}
          onLogout={this.handleLogout}
        />
        <div className="app-content">
          <ToastContainer autoClose={1500} />
          <Container>
            <Switch>
              <Route
                exact
                path="/"
                isAuthenticated={this.state.isAuthenticated}
                currentUser={this.state.currentUser}
                handleLogout={this.handleLogout}
                {...this.props}
              ></Route>
              <Route
                path="/login"
                render={(props) => (
                  <Login onLogin={this.handleLogin} {...props} />
                )}
              ></Route>
              <Route path="/signup" component={Signup}></Route>
              <Route path="/users/:username" 
                  render={(props) => <Profile isAuthenticated={this.state.isAuthenticated} currentUser={this.state.currentUser} {...props}  />}>
            </Route>
            <Route authenticated={this.state.isAuthenticated} path="/direction/new" component={NewDirection}></Route>
            <Route  path="/direction/" component={Directions}></Route>
            <Route component={NotFound}></Route>              
            </Switch>
          </Container>
        </div>
      </div>
    );
  }
}

export default withRouter(App);