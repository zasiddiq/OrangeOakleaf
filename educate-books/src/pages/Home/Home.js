import React from 'react'
import { Spin } from "antd"
import * as BooksAPI from '../../utils/BooksAPI'
import { groupBy } from '../../utils'
import BookItem from '../../components/BookCard/BookCard'
import './Home.css'

class BooksApp extends React.Component {
    state = {
        bookList: [],
    }

    componentDidMount() {
        this.getBookList()
    }


    getBookList = async () => {
        let bookList = await BooksAPI.getAll()

        let bookGroups = groupBy(bookList, 'shelf')
        this.setState({
            bookList: bookList,
            bookGroups: bookGroups
        })

    }

    render() {
        const { bookList } = this.state
        return (

            <div className="home-books-results">
                <Spin tip="加载中..." spinning={bookList.length > 0 ? false : true}>
                    <ol className="books-grid">
                        {
                            // 🔧 fix2:  Search results are not shown when all of the text is deleted out of the search input box.
                            bookList.map(bookItem => <BookItem key={bookItem.id} bookList={bookList} getBookList={this.getBookList} bookItem={bookItem} />)
                        }
                    </ol>
                </Spin>

            </div>
        )
    }
}

export default BooksApp
