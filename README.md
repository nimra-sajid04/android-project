# 🛒 Online Shop Android Application

## 📌 Project Information

- **Name:** Nimra Sajid  
- **Registration No:** 23-ARID-846  
- **Subject:** App Development  
- **Submitted To:** Sir Azhar
- **Project Screenshots Document:** [Document](https://github.com/nimra-sajid04/android-project/blob/master/Assignment%203%20and%204.docx)

---

## 📱 Project Overview

This repository contains an **Android application for an Online Shop**, developed using **Java** and **SQLite** in **Android Studio**.  
The application demonstrates fundamental Android development concepts including **Activities, Intents, RecyclerView, SQLite database, SharedPreferences, Themes, and Menus**.

The project can be **cloned or downloaded** and executed using:
- Android Emulator
- Physical Android device

---

## 🚀 Features Implemented

### 🔐 User Authentication
- Login and Signup functionality
- Authentication state managed using `SharedPreferences`
- Automatic redirection based on login status

### 🎨 Theme Management
- Light and Dark themes supported
- Theme switching via Options Menu
- Selected theme persisted using `SharedPreferences`
- UI updates automatically on theme change

### 🗄️ Local Data Persistence (SQLite)
- SQLite database used for storing:
  - Users
  - Items
  - Orders
- Full CRUD operations implemented
- Database versioning handled using `onUpgrade()`

### 🛍️ Item Management
- Product list displayed using `RecyclerView`
- Dynamic item images loaded using image resource IDs
- Item details screen with quantity selection

### 🛒 Order & Cart Management
- Add items to cart
- Place orders with delivery details
- Orders stored and retrieved from SQLite
- Order history with detailed view

### 📦 Delivery Details
- Capture delivery **Name**, **Phone Number**, and **Address**
- Store delivery information with each order
- Display delivery details in Order Details screen

### 📋 Menus & Navigation
- Options Menu available on all screens except Login and Signup
- Context Menu on long-press to delete items
- Navigation between screens using Intents

### 🧠 UI & Interaction
- Material Design principles followed
- Input validation for user forms
- Dynamic UI updates on data changes

---

## 🧩 Activities Description

### 🔑 LoginActivity
Handles user login and redirects authenticated users to the main application.

### 📝 SignupActivity
Allows new users to register and stores user information locally.

### 🛍️ ItemsActivity
Displays all available items using a RecyclerView with images, names, and prices.

### 🔍 ItemDetailsActivity
Shows detailed information about a selected item and allows quantity selection.

### 🛒 ViewCartActivity
Displays selected items before order placement.

### 📦 PlaceOrderActivity
Collects delivery details and confirms order placement.

### 📜 OrdersActivity
Displays a list of all orders placed by the user.

### 📄 OrderDetailsActivity
Shows complete order information including delivery name, phone, and address.

### 👤 UserProfileActivity
Displays user profile information and account details.

---

## 🛠️ Technologies Used

- **Programming Language:** Java  
- **IDE:** Android Studio  
- **Database:** SQLite  
- **UI Design:** XML, Material Components  
- **Architecture:** Activities, Adapters, Models, Utilities  

---

## ▶️ How to Run the Project

1. Clone the repository:
   ```bash
   git clone https://github.com/nimra-sajid04/android-project.git
