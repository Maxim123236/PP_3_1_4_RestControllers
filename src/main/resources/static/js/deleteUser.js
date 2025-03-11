$(async function () {
    deleteUser();
});

async function deleteUser() {
    const deleteForm = document.forms["formDeleteUser"];

    deleteForm.addEventListener("submit", async (ev) => {
        ev.preventDefault();
        const userId = deleteForm.id.value;

        try {
            await fetch(`http://localhost:8080/api/admin/${userId}`, {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json'
                }
            });
            $('#deleteFormCloseButton').click();
            allUsers();
        } catch (error) {
            console.error("Error deleting user:", error);
        }
    });
}

async function getUser(id) {
    const url = `http://localhost:8080/api/user/${id}`;
    const response = await fetch(url);
    return await response.json();
}

$('#delete').on('show.bs.modal', async (ev) => {
    const button = $(ev.relatedTarget);
    const userId = button.data('id');
    await showDeleteModal(userId);
});

async function showDeleteModal(id) {
    const user = await getUser(id);
    const form = document.forms["formDeleteUser"];

    form.id.value = user.id;
    form.firstName.value = user.firstName;
    form.lastName.value = user.lastName;
    form.age.value = user.age;
    form.email.value = user.email;

    const rolesResponse = await fetch("http://localhost:8080/api/role");
    const roles = await rolesResponse.json();

    const rolesSelect = $('#deleteRoles').empty();
    roles.forEach(role => {
        const isSelected = user.roles.some(userRole => userRole.name === role.name);
        const option = new Option(role.name, role.id, isSelected, isSelected);
        rolesSelect.append(option);
    });
}