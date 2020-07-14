import React, { Component } from "react";
import { connect } from "react-redux";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import Home from "./pages/Home/Home";

import Nav from "./components/Nav";
import Login from "./pages/Login/Login";
import { Fragment } from "react";
import Footer from "./components/Foot";
// import './App.css'
class App extends Component {
  componentDidMount() {

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
                <Login />
              )} />
              :
              <Fragment>
                <Switch>
                  <Route path="/" exact component={Home} />
                  {/* <Route path="/" component={MyBookList} /> */}
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
