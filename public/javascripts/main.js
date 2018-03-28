//url = "http://localhost:9000/";
tbody = document.querySelector('tbody');

if (tbody) {
    console.log("tbody is true");
    console.log(tbody);
    tbody.addEventListener("click", function (ev) {
        if(/*ev.target.dataset.action == 'delete' &&*/ ev.target.dataset.action == 'delete' && confirm("Dou you really want to delete the note?"))
        deleteItem(ev.target.dataset.id);
    });



    function deleteItem(id) {
        return fetch('/notes/' + id, {
            method: 'delete'
        }).then(function (value) {
            location.reload();
        }).then(function (value) {
            location.reload();
        });
        //.then(response => console.log(response));
    }

}


/*
tbody = document.querySelector('tbody');


if (tbody) {
    tbody.addEventListener('click', function (e) {
        if (e.target.dataset.action == 'delete' && confirm("Dou you really want to delte the note?")){
            fetch('/notes' + e.target.dataset.id, {
                methode: 'DELETE'
            }).then(function (value) {
                location.reload();
            })
    }
    });
}*/