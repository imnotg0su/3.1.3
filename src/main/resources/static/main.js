// event.preventDefault(); отменяет перезагрузку страницы
let userInfo = $('#usersTable')
let getAllUser = []
const usersURL = 'http://localhost:8080/api/users/';
const rolesURL ='http://localhost:8080/api/users/roles';

getAllUsers()

function getAllUsers() {
    fetch(usersURL).then((response) => {
        response.json().then((users) => {
            users.forEach((user) => {
                usersTable(user)
                getAllUser.push(user)
            });
        });
    });
}
function usersTable(user) {
    userInfo.append(
        `<tr> 
                 <td> ${user.id} </td> 
                 <td> ${user.username} </td> 
                 <td> ${user.surname} </td> 
                 <td> ${user.age} </td> 
                 <td> ${user.email} </td> 
                 <td> ${user.roles.map(roles => roles.name)} </td> 
                 <td> 
                 <button onclick="editUser(${user.id})" class="btn btn-info edit-btn" data-bs-toggle="modal" data-bs-target="#editModal">Edit</button></td>
                 <td> 
                 <button onclick="deleteCurrentUser(${user.id})" class="btn btn-danger">Delete</button></td>
        </tr>`
    )
}


function deleteCurrentUser(id) {

    fetch(usersURL + id, {method: "DELETE"})
        .then(() => {
            userInfo.empty();
            getAllUsers();
        })
}

function arrOfRoles(arr) {
    let roles = [];

    if (arr.indexOf("ROLE_ADMIN") >= 0) {
        roles.push({"id": 1, "name": "ROLE_ADMIN", "authority" : "ROLE_ADMIN"});
    }

    if (arr.indexOf("ROLE_USER") >= 0) {
        roles.push({"id": 2, "name": "ROLE_USER", "authority" : "ROLE_USER"});
    }

    return roles;
}


document.querySelector('#addNewUserButton').addEventListener('click', (event) => {
    event.preventDefault();
    let newUser = {
        id: $('#newId').val(),
        username: $('#usernameN').val(),
        surname: $('#surnameN').val(),
        age: $('#ageN').val(),
        email: $('#emailN').val(),
        password: $('#passwordN').val(),
        roles: arrOfRoles(Array.from(document.getElementById('addRole').selectedOptions)
            .map(role => role.value))
    }

    fetch(usersURL, {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        },
        body: JSON.stringify(newUser)

    }).then(() => location.reload())
})

function editUser(id) {
    fetch(usersURL + id, {method: 'GET', dataType: 'json',})
        .then(res => {
            res.json().then(user => {
                $('#editId').val(user.id)
                $('#editFirstName').val(user.username)
                $('#editLastName').val(user.surname)
                $('#editAge').val(user.age)
                $('#editEmail').val(user.email)
            })
        })
}

document.querySelector('#editButton').addEventListener('click', (event) => {
    event.preventDefault();
    let editUser = {
        id: $('#editId').val(),
        username: $('#editFirstName').val(),
        surname: $('#editLastName').val(),
        age: $('#editAge').val(),
        email: $('#editEmail').val(),
        password: $('#editPassword').val(),
        roles: arrOfRoles(Array.from(document.getElementById('editRole').selectedOptions)
            .map(role => role.value))
    }

    fetch(usersURL, {
        method: "PATCH",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        },
        body: JSON.stringify(editUser)
    }).then(() => location.reload())

})



