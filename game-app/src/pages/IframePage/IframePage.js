import React, { Component } from 'react';
import { Spin } from 'antd';

class IframePage extends Component {
  state = {
    isLoading: true,
  };

  componentDidMount() {

    this.iframeEl.onload = () => {
      this.setState({
        isLoading: false,
      });
    };
  }

  render() {
    const { isLoading } = this.state;
    // 打平之后的菜单
    const query = this.props.location.query || {};

    return (
      <div
        className="iframe-container"
        style={{
          paddingLeft: 10,
          width: 1024,
          height: 798,
          WebkitOverflowScrolling: 'touch',
          background: 'rgb(245, 247, 250)',
          // overflow-y: scroll;
          // pointerEvents: 'none'
        }}
      >
        {isLoading ? (
          <div
            style={{
              position: 'absolute',
              zIndex: 1000,
              left: 0,
              top: 0,
              bottom: 0,
              right: 0,
              display: 'flex',
              alignItems: 'center',
              justifyContent: 'center',
              background: 'rgb(245, 247, 250)',
            }}
          >
            <Spin />
          </div>
        ) : null}
        <iframe
          title="外链"
          ref={el => (this.iframeEl = el)}
          allowtransparency="no"
          src={query.url}
          frameBorder="0"
          width={'100%'}
          height={'100%'}
          scrolling="yes"
        // sandbox="allow-scripts allow-same-origin allow-popups"
        />
      </div>
    );
  }
}

export default IframePage;
