class App {
    static SweetAlert = class {
        static showSuccessAlert(t) {
            Swal.fire({
                icon: 'success',
                title: t,
                position: 'top-end',
                showConfirmButton: false,
                timer: 1500
            })
        }

        static showErrorAlert(t) {
            Swal.fire({
                icon: 'error',
                title: 'Warning',
                text: t,
            })
        }
    }
    static IziToast = class  {
        static showSuccessAlert(m) {
            iziToast.success({
                title: 'Success',
                position: 'topRight',
                timeout: 2500,
                message: m,
            });
        }
        static showErrorAlert(m) {
            iziToast.error({
                title: 'Error',
                position: 'topRight',
                timeout: 2500,
                message: m,
            });
        }
    }
}

class Product {
    constructor(id,color, productName, quantity, price, description, image, category,createdAt,updatedAt) {
        this.color=color;
        this.id = id;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
        this.image = image;
        this.category = category;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}

class Category {
    constructor(id, name) {
        this.id = id;
        this.name = name;
    }
}
class Role {
    constructor(id, code) {
        this.id = id;
        this.code = code;
    }
}

class User {
    constructor(id,  username, password, role, status) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.status = status;
    }
}



