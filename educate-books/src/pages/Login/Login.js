import React, { Component } from 'react';
import { Card } from "antd"

import LoginFrom from './LoginFrom/LoginFrom'
import './Login.scss'
import {
  Row,
} from 'antd';

export class Login extends Component {
  state = {
    loading: false
  };
  handleLoading = () => {
    this.setState({ loading: true });
  };

  render() {
    const { loading } = this.state
    return (
      <div className="login-box flex-wrap flex-center flex-middle flex-direction-column">
        <Card title={
          <>
            <h3 align="center">Welcome to the App!</h3>
          </>
        } style={{ width: '100%' }}>
          <LoginFrom onLoading={this.handleLoading} />
        </Card>

      </div>
    );
  }
}

export default Login;