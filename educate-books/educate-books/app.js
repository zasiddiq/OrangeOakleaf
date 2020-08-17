var express = require('express');
var app = express();
var proxy = require('http-proxy-middleware');
var bodyParser = require('body-parser')
const config = require('./config.js');


// 代理
app.use('/_api2', proxy.createProxyMiddleware({
    // 代理跨域目标接口
    target: config.api2,
    changeOrigin: true,
    secure: false,
    pathRewrite: {
        '^/_api2': '/'
    }
}));

//注册路由
// app.use(bodyParser.json())
app.use(express.static('./build'));
const book = require('./routes/book')

app.use('/book', book)

app.get('/', (req, res) => {
    res.render('./build/index.html')
})

var server = app.listen(config.port, function () {
    var host = server.address().address;
    var port = server.address().port;

    console.log(`Example app listening at ${config.url}:${port}`);
});
