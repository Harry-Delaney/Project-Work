class shopping_cart:

    def __init__(self):
        self.items = []


    def add_item(self, name, price, quantity):

        #Checks if the item is already in the Cart
        for item in self.items:
            if item["name"] == name:
                item["quantity"] += quantity
                print(f"Updated quantity of {name} to {item['quantity']}.")
                return

        #Adds it to the cart if it is not already in it
        self.items.append({"name": name, "price": price, "quantity": quantity})
        print(f"Added {quantity} of new item {name} to shopping cart.")

    def remove_item(self, name):
        """Remove one instance of an item in the cart by its product name"""
        for item in self.items:
            if item["name"] == name:
                if item["quantity"] > 1:
                    item["quantity"] -= 1  # Decrease quantity by 1
                    print(f"Reduced quantity of {name} by 1.")
                else:
                    self.items.remove(item)  # If quantity is 1, remove the item from the cart
                    print(f"Removed {name} from the cart.")
                return
        print(f"Item {name} is not in the cart.")

    def update_quantity(self, name, quantity):

        for item in self.items:
            if item["name"] == name:
                if quantity <= 0:
                    print("Invalid Quantity")
                else:
                    item["quantity"] = quantity
                    print(f"Updated quantity of {name} to {item['quantity']}.")
                    return
        print(f"Item {name} is not in the cart.")

    def get_total(self):
        total = 0
        for item in self.items:
            total += item["price"] * item["quantity"]
        return total

    def view_cart(self):
        for item in self.items:
            print(f"Name: {item['name']}")
            print(f"Price: {item['price']}")
            print(f"Quantity: {item['quantity']}")
            print(f"Total: {item['price'] * item['quantity']}")
        return

    def empty_cart(self):

        self.items = []
        print(f"Empty cart.")

        return

cart = shopping_cart()
cart.add_item("Laptop", 1200.00, 1)
cart.add_item("Headphones", 150.00, 2)
cart.add_item("Mouse", 25.00, 3)

# View the cart
cart.view_cart()

# Update quantity of an item
cart.update_quantity("Headphones", 3)

# Remove an item from the cart
cart.remove_item("Mouse")

# View cart again after modifications
cart.view_cart()

# Get the total price of the items
print(f"Total price: €{cart.get_total():.2f}")

# Clear the cart
cart.empty_cart()

# View cart after clearing
cart.view_cart()
