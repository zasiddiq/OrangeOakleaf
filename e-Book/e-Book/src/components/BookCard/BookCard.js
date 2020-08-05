
import React, { Fragment } from 'react'
import { Link } from "react-router-dom";


import * as BooksAPI from '../../utils/BooksAPI'
import './BookCard.css'

class BookItemComponent extends React.Component {
    constructor() {
        super()
        this.state = {

        }
    }

    handleChange = async (event, bookItem) => {
        const title = event.target.value;

        await this.moveToBookShelf(bookItem, title);
        await this.props.getBookList();
    }

    moveToBookShelf = async (bookItem, title) => {
        let data = await BooksAPI.update(bookItem, title);
    }

    render() {
        const { bookItem, bookList } = this.props;
        // 🔧 fix3: The search works correctly when a book does not have a thumbnail or an author. (To test this, try searching for "poetry" and "biography")  
        const { id, title, imageLinks = {}, authors = [], cover, url
        } = bookItem;

        // 🔧 fix4 and fix 6: If a book is assigned to a shelf on the main page and that book appears on the search page, the correct shelf should be selected on the search page.
        const currentBookItem = bookList.find(item => bookItem.id === item.id) || bookItem;

        return (
            <Fragment>
                <Link to={
                    {
                        pathname: 'iframe',
                        query: {
                            url: url
                        }
                    }}>
                    <li key={id} >
                        <div className="book">
                            <div className="book-top">
                                <div className="book-cover" style={{ width: 128, height: 193, backgroundImage: cover ? `url(${cover})` : '' }}></div>
                                {/* <div className="book-shelf-changer">
                                {
                                    <select value={currentBookItem.shelf || 'none'} onChange={(e) => this.handleChange(e, bookItem)}>
                                        <option value="move" disabled>Move to...</option>
                                        <option value="currentlyReading">Currently Reading</option>
                                        <option value="wantToRead">Want to Read</option>
                                        <option value="read">Read</option>
                                        <option value="none">None</option>
                                    </select>}
                            </div> */}
                            </div>
                            <div className="book-title">{title}</div>
                            <div className="book-authors">{authors.join()}</div>
                        </div>
                    </li>
                </Link>

            </Fragment>)
    }
}

export default BookItemComponent
