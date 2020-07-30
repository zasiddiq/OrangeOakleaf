import request from '../utils/request'

//请求逻辑实例化

const Book = {

    getList: (params) => {
        let data = request.get('/book/list', params)
            .then((response) => {
                return response
            })
        return data
    }
}

export default Book