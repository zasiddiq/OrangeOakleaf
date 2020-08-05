var express = require('express');
var app = express();
var bodyParser = require('body-parser')

app.use(bodyParser.json())

//注册路由

app.use(express.static('./build'));
const book = require('./routes/book')
const config = require('./config.js');

app.use('/book', book)

app.get('/', (req, res) => {
    res.render('./build/index.html')
})

// app.get('/book', (req, res) => {
//     var readDir = fs.readdirSync("./");
// console.log(readDir);
// })

var server = app.listen(config.port, function () {
    var host = server.address().address;
    var port = server.address().port;

    console.log('Example app listening at http://%s:%s', host, port);
});
