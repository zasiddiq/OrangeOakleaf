import React, { Component } from 'react';
import { UserOutlined, LockOutlined } from '@ant-design/icons';
import { connect } from 'react-redux';

import {
  Form,
  Button,
  Input,
  Row,
  message
} from 'antd';
import { setAuthUser } from '../../../actions/authUser';


class LoginForm extends Component {
  static propTypes = {

  };
  state = {
    value: ''
  };
  handleSubmit = values => {

    const { setAuthUser } = this.props;

    const { username, password } = values
    if (username === 'admin' && password === '123') {
      new Promise((res, rej) => {
        // showLoading();
        message.success('登陆成功')
        setTimeout(() => res(), 500);
      }).then(() => setAuthUser(username));
    } else {
      message.error('账号密码错误')
    }

  };
  generateDropdownData = () => {
    const { users } = this.props;

    return users.map(user => ({
      key: user.id,
      text: user.name,
      value: user.id,
      image: { avatar: true, src: user.avatarURL }
    }));
  };
  render() {

    return (

      <Form style={{ width: '100%' }} onFinish={this.handleSubmit} className="login-form">
        <Form.Item
          name="username"
          rules={[
            {
              required: true,
              message: 'Please input your Username!',
            },
          ]}
        >
          <Input prefix={<UserOutlined className="site-form-item-icon" />} placeholder="Username" />
        </Form.Item>
        <Form.Item
          name="password"
          rules={[
            {
              required: true,
              message: 'Please input your Password!',
            },
          ]}
        >
          <Input
            prefix={<LockOutlined className="site-form-item-icon" />}
            type="password"
            placeholder="Password"
          />
        </Form.Item>
        <Form.Item>
          <Row justify="center">
            <Button type="primary" htmlType="submit" className="login-form-button">Login</Button>
          </Row>
        </Form.Item>
      </Form>
    );
  }
}

export default connect(
  ({ users }) => ({
    users: Object.values(users)
  }),
  { setAuthUser }
)(LoginForm);

