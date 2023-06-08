
const apiUrl = '/books';


const form = document.getElementById('add-book-form');
const list = document.getElementById('book-list');


fetch(apiUrl)
    .then(response => response.json())
    .then(books => {
        for (let book of books) {
            addBookToList(book);
        }
    });


function addBookToList(book) {
    const li = document.createElement('li');
    li.textContent = `${book.title} by ${book.author}`;
    list.appendChild(li);
}


form.addEventListener('submit', event => {
    event.preventDefault();


    const book = {
        title: form.title.value,
        author: form.author.value
    };


    fetch(apiUrl, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(book),
    })
    .then(response => response.json())
    .then(book => {
        // Clear the form
        form.title.value = '';
        form.author.value = '';


        addBookToList(book);
    });
});
