// src/components/Cart.js
import React, { useState } from 'react';

const Cart = () => {
    const [cartItems, setCartItems] = useState([]);
    const [products] = useState([
        { id: 1, name: 'Product 1', price: 10 },
        { id: 2, name: 'Product 2', price: 20 },
        { id: 3, name: 'Product 3', price: 30 },
    ]);

    const handleAddItem = (product) => {
        setCartItems((prevItems) => [...prevItems, product]); // Add product to cart
    };

    const handleRemoveItem = (itemToRemove) => {
        setCartItems((prevItems) => prevItems.filter(item => item.id !== itemToRemove.id)); // Remove item from cart
    };

    return (
        <div>
            <h1>Your Shopping Cart</h1>
            <div>
                <h2>Available Products</h2>
                <ul>
                    {products.map((product) => (
                        <li key={product.id}>
                            {product.name} - ${product.price}
                            <button onClick={() => handleAddItem(product)}>Add to Cart</button>
                        </li>
                    ))}
                </ul>
            </div>
            <div>
                <h2>Cart Items</h2>
                {cartItems.length === 0 ? (
                    <p>This is where the cart items will be displayed.</p>
                ) : (
                    <ul>
                        {cartItems.map((item, index) => (
                            <li key={index}>
                                {item.name} - ${item.price} 
                                <button onClick={() => handleRemoveItem(item)}>Remove</button>
                            </li>
                        ))}
                    </ul>
                )}
            </div>
        </div>
    );
};

export default Cart;

