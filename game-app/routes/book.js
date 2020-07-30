const express = require('express')
const fs = require('fs')
const path = require('path');
const config = require('../config.js');
// 使用 express.Router 可以创建模块化的路由
const main = express.Router()


main.get('/list', async (request, response) => {

  let ret = {
    "success": true,
    "code": 200,
    "message": "",
    "data": [],
  }

  const booksDir = path.resolve(__dirname, '..', 'build/books');
  var readDir = fs.readdirSync(booksDir);

  const datas = readDir.map(e => ({
    url: `/books/${e}/index.html`,
    cover: `/books/${e}/cover.jpeg`,
    title: e,
    id: e,
  }))

  ret.data = datas
  response.send(ret)
})


module.exports = main
