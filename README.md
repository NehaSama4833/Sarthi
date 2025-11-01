# 🚚 Sarthi - Booking & Shifting Services App

<div align="left">

![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Firebase](https://img.shields.io/badge/Firebase-039BE5?style=for-the-badge&logo=Firebase&logoColor=white)
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)

**Moving Made Easy** - An Android application designed to simplify booking and shifting services across India.

[Features](#-features) •  [Installation](#-installation) • [Tech Stack](#-tech-stack) • [Usage](#-usage) • [Future Roadmap](#-future-roadmap) • [Contributing](#-contributing)

</div>

---

## 📖 About

**Sarthi** is a comprehensive Android mobile application that revolutionizes the way people book moving and shifting services. Whether you're relocating to a new city or moving within your current location, Sarthi provides a seamless, user-friendly platform to book vehicles, schedule services, and manage your moving experience.

The app connects users with professional shifting services across 30+ major Indian cities, offering multiple vehicle options and helper services to make your move hassle-free.

## ✨ Features

### 🔐 Authentication & Security
- **Secure Login/Signup** - Firebase Authentication for safe user accounts
- **Profile Management** - Update personal information and preferences
- **Session Management** - Automatic login persistence

### 📦 Booking Services
- **Easy Booking** - Simple interface to book moving services
- **Multiple Vehicle Types** - Choose from Small, Medium, or Large trucks
- **Helper Options** - Select 0-3 helpers based on your needs
- **Location Selection** - Pick from 30+ major Indian cities
- **Date & Time Scheduling** - Flexible scheduling for your convenience

### 📋 Booking Management
- **My Bookings** - View all your current and past bookings
- **Booking Details** - Comprehensive booking information
- **Status Tracking** - Real-time booking status updates
- **Booking History** - Access complete booking records

### 💬 Feedback System
- **Rating System** - Rate your experience with star ratings
- **Comments** - Provide detailed feedback about the service
- **Feedback History** - Track all your submitted feedback

### 🎨 User Experience
- **Modern UI/UX** - Clean, intuitive Material Design interface
- **Splash Screen** - Branded welcome screen
- **Navigation Drawer** - Easy access to all features
- **Responsive Design** - Works seamlessly on various screen sizes
- **Loading Indicators** - Progress feedback for all operations


## 🛠️ Tech Stack

### Core Technologies
- **Language**: Java
- **Platform**: Android (API Level 21+)
- **Build System**: Gradle 8.1.2
- **Min SDK**: 21 (Android 5.0 Lollipop)
- **Target SDK**: 34 (Android 14)
- **Compile SDK**: 34

### Backend & Services
- **Firebase Authentication** (22.1.2) - User authentication and security
- **Firebase Realtime Database** (20.3.0) - Real-time data synchronization
- **Firebase Storage** (20.3.0) - File and media storage
- **Firebase Analytics** (21.5.0) - User analytics and insights

### Libraries & Dependencies
- **AndroidX Core KTX** (1.12.0) - Kotlin extensions
- **Material Components** (1.11.0) - Modern UI components
- **ConstraintLayout** (2.1.4) - Flexible layout system
- **Navigation Components** (2.7.6) - In-app navigation
- **CircleImageView** (3.1.0) - Circular profile images
- **Google Services** (4.4.0) - Firebase integration

### Architecture
- **MVC Pattern** - Model-View-Controller architecture
- **View Binding** - Type-safe view references
- **Utility Classes** - Reusable helper components

## 📦 Installation

### Prerequisites
- Android Studio (Arctic Fox or later recommended)
- JDK 8 or higher
- Android SDK with API Level 34
- Firebase account for backend services

### Setup Instructions

1. **Clone the Repository**
   ```bash
   git clone https://github.com/yourusername/Sarthi-App-Final.git
   cd Sarthi-App-Final
   ```

2. **Firebase Configuration**
   - Create a new Firebase project at [Firebase Console](https://console.firebase.google.com/)
   - Add your Android app with package name: `com.example.sarthi_app`
   - Download `google-services.json`
   - Replace the existing file in `app/google-services.json`

3. **Build the Project**
   - Open the project in Android Studio
   - Sync Gradle files
   - Build the project (Ctrl+F9 or Cmd+F9)
   - Run on an emulator or physical device

4. **Configure Firebase Rules** (for production)
   ```javascript
   {
     "rules": {
       "users": {
         "$uid": {
           ".read": "$uid === auth.uid",
           ".write": "$uid === auth.uid"
         }
       },
       "bookings": {
         "$uid": {
           ".read": "$uid === auth.uid",
           ".write": "$uid === auth.uid"
         }
       }
     }
   }
   ```

## 🚀 Usage

### For End Users

1. **Getting Started**
   - Launch the app to see the splash screen
   - Sign up with email and password or log in
   - Complete your profile with personal details

2. **Booking a Move**
   - Tap "Book a Move" from the home screen
   - Enter pickup and dropoff locations
   - Select date and time for the move
   - Choose vehicle type and helper count
   - Confirm your booking

3. **Managing Bookings**
   - View all bookings in "My Bookings"
   - Check status and details
   - Provide feedback after service completion

4. **Profile & Settings**
   - Update your information anytime
   - Change password securely
   - Log out when needed

## 🔮 Future Roadmap

### Planned Features (Version 2.0+)
- 🤖 **AI-Powered Room Scanning** - Automatic dimension and volume calculation
- 🥽 **AR Previews** - Augmented Reality furniture placement preview
- 🎯 **Smart Driver Matching** - AI-based driver and vehicle recommendation
- 📍 **Real-time GPS Tracking** - Live tracking of your shipment
- 💳 **In-App Payments** - Secure payment gateway integration
- 📸 **Photo Verification** - Before/after moving photos
- 🔔 **Push Notifications** - Real-time booking updates
- 🌐 **Multi-language Support** - Regional language support
- ⭐ **Driver Ratings** - Rate and review drivers
- 🎁 **Loyalty Program** - Rewards for frequent users

### Technical Improvements
- Migrate to Kotlin for better performance
- Implement MVVM architecture
- Add unit and integration tests
- Optimize database queries
- Implement offline mode support
- Add dark theme support

## 📁 Project Structure

```
Sarthi-App-Final/
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── java/com/sarthi/MoveApp/
│   │       │   ├── adapters/          # RecyclerView adapters
│   │       │   ├── models/            # Data models
│   │       │   ├── utils/             # Utility classes
│   │       │   └── *.java             # Activities
│   │       ├── res/
│   │       │   ├── drawable/          # Images & icons
│   │       │   ├── layout/            # XML layouts
│   │       │   ├── menu/              # Navigation menus
│   │       │   └── values/            # Resources
│   │       └── AndroidManifest.xml
│   ├── build.gradle                   # App dependencies
│   └── google-services.json           # Firebase config
├── build.gradle                       # Project build file
├── settings.gradle                    # Gradle settings
└── README.md                          # This file
```

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

### Guidelines
- Follow Java coding conventions
- Write meaningful commit messages
- Update documentation as needed
- Test thoroughly before submitting

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👥 Authors

**Neha Sharma** - *Initial work* - [GitHub Profile](https://github.com/NehaSama4833)

## 🙏 Acknowledgments

- Firebase team for excellent backend services
- Material Design team for UI guidelines
- All contributors and testers
- The open-source community

## 📞 Support

For support, contact us on linkedIn : https://www.linkedin.com/in/neha-sharma-b08737316/ or create an issue in the repository.

## 📊 Project Status

![Status](https://img.shields.io/badge/status-active-success.svg)
![Version](https://img.shields.io/badge/version-1.0-blue.svg)

---

<div align="center">

**Made with ❤️ in India**

⭐ Star this repo if you find it helpful!

</div>
