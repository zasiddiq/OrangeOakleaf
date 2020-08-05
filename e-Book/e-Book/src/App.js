import React, { Component } from "react";
import { connect } from "react-redux";
import { BrowserRouter as Router, Route, Switch, Redirect } from "react-router-dom";
import Home from "./pages/Home/Home";
import IframePage from "./pages/IframePage/IframePage";

import Nav from "./components/Nav";
import Login from "./pages/Login/Login";
import Welcome from "./pages/Welcome/Welcome";
import { Fragment } from "react";

import Footer from "./components/Foot";
// import './App.css'
class App extends Component {
  componentDidMount() {
    //this.context.router.transitionTo('/');
    this.setState({ redirect: "/" });
  }

  render() {
    const { authUser, loading } = this.props;

    return (
      <Router>
        <Fragment>
          {authUser && <Nav />}
          <div className="container flex-wrap flex-center flex-middle flex-direction-column">
            {!authUser
              ?
              <Route render={() => (
                <Fragment>
                  <Switch>
                    <Route path="/" exact component={Welcome} />
                    <Route path="/login" exact component={Login} />
                  </Switch>
                </Fragment>
              )} />
              :
              <Fragment>
                <Switch>
                  <Route path="/" exact component={Home} />
                  <Route path="/iframe" component={IframePage} />
                </Switch>
                {/* <Footer /> */}
              </Fragment>
            }
          </div>
        </Fragment>

      </Router>
    );
  }
}

export default connect(({ authUser, LoadingBar }) => ({ authUser, loading: LoadingBar }))(App);
