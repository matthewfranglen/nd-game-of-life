import React from 'react';
import { Link } from 'react-router';
import { Navbar, Nav, NavItem } from 'react-bootstrap';
import { IndexLinkContainer, LinkContainer } from 'react-router-bootstrap';

const Header = () => (
  <Navbar className="header">
    <Navbar.Header>
      <Navbar.Brand className="header__brand">
        <Link className="brand" to="/">
          N Dimensional Game of Life
        </Link>
      </Navbar.Brand>
    </Navbar.Header>
    <Nav pullRight>
      <IndexLinkContainer to="/">
        <NavItem>Home</NavItem>
      </IndexLinkContainer>
      <LinkContainer to="/about">
        <NavItem>About</NavItem>
      </LinkContainer>
    </Nav>
  </Navbar>
);

export default Header;
