//url = "http://localhost:9000/";


document.querySelector("tbody").addEventListener("click", function (ev) {

    deleteItem(ev.target.dataset.id);

});

function deleteItem(id) {

    console.log('./notes/' + id);

    return fetch('./notes/' + id, {
        method: 'delete'
    });
        //.then(response => console.log(response));
}