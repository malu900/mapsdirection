import React, { Component } from "react";
import { Nav, Navbar, Container, NavDropdown, NavItem } from "react-bootstrap";
import { Link } from "react-router-dom";
import { withRouter } from "react-router-dom";

export class AppHeader extends Component {
  constructor(props) {
    super(props);
  }

  handleMenuClick = () => {
    this.props.onLogout();
  };

  render() {
    let menuItems;
    if (this.props.currentUser) {
      menuItems = [
        <Container key="loggedincontainer">
          <Navbar.Brand key="/">
            <Link to="/"> HOME </Link>
          </Navbar.Brand>
          <div className="username-info">
            signed in as: {this.props.currentUser.username}
          </div>
          <NavItem key="/direction"> <Link to="/direction">Directions</Link></NavItem>
          <NavDropdown title="Dropdown" id="basic-nav-dropdown">
            <Nav.Item key="profile"> <Link to={`/users/${this.props.currentUser.username}`}> Profile </Link></Nav.Item>
            <NavItem key="/direction/new"> <Link to="/direction/new">New</Link></NavItem>
            <NavDropdown.Divider />
            <NavDropdown.Item onClick={() => this.handleMenuClick()}>
              logout
            </NavDropdown.Item>
          </NavDropdown>
        </Container>,
      ];
    } else {
      menuItems = [
        <Container key="notloggedincontainer">
          <Navbar.Brand key="/">
            <Link to="/"> Home </Link>
          </Navbar.Brand>
          <Container>
            <Nav.Item key="/login">
              <Link to="/login"> Login </Link>
            </Nav.Item>
            <Nav.Item key="/signup">
              <Link to="/signup"> Signup</Link>
            </Nav.Item>
          </Container>
        </Container>,
      ];
    }

    return (
      <Navbar collapseOnSelect expand="lg" bg="light" variant="light">
        {menuItems}
      </Navbar>
    );
  }
}

export default withRouter(AppHeader);
