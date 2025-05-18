CREATE DATABASE  IF NOT EXISTS `furniture` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `furniture`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: furniture
-- ------------------------------------------------------
-- Server version	8.0.35

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `brands`
--

DROP TABLE IF EXISTS `brands`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `brands` (
  `brand_id` int NOT NULL,
  `brand_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`brand_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brands`
--

LOCK TABLES `brands` WRITE;
/*!40000 ALTER TABLE `brands` DISABLE KEYS */;
/*!40000 ALTER TABLE `brands` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `cart_id` int NOT NULL,
  `quantity` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  PRIMARY KEY (`cart_id`),
  KEY `user_id` (`user_id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `cart_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `cart_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `category_id` int NOT NULL,
  `category_name` varchar(255) DEFAULT NULL,
  `category_description` text,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favorites`
--

DROP TABLE IF EXISTS `favorites`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `favorites` (
  `favorite_id` int NOT NULL,
  `user_id` int DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  PRIMARY KEY (`favorite_id`),
  KEY `user_id` (`user_id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `favorites_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `favorites_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favorites`
--

LOCK TABLES `favorites` WRITE;
/*!40000 ALTER TABLE `favorites` DISABLE KEYS */;
/*!40000 ALTER TABLE `favorites` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_items`
--

DROP TABLE IF EXISTS `order_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_items` (
  `order_item_id` int NOT NULL,
  `order_quantity` int DEFAULT NULL,
  `order_price` float DEFAULT NULL,
  `order_id` int DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  PRIMARY KEY (`order_item_id`),
  KEY `order_id` (`order_id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `order_items_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`),
  CONSTRAINT `order_items_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_items`
--

LOCK TABLES `order_items` WRITE;
/*!40000 ALTER TABLE `order_items` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `order_id` int NOT NULL,
  `total_price` float DEFAULT NULL,
  `order_status` varchar(20) DEFAULT NULL,
  `payment_status` varchar(255) DEFAULT NULL,
  `payment_method` varchar(255) DEFAULT NULL,
  `created_at` date DEFAULT NULL,
  `order_note` varchar(255) DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `product_id` int NOT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  `product_total` int DEFAULT NULL,
  `product_price` float DEFAULT NULL,
  `product_discount_price` float DEFAULT NULL,
  `product_description` text,
  `product_visited` int DEFAULT NULL,
  `product_image_url` varchar(255) DEFAULT NULL,
  `product_size` varchar(255) DEFAULT NULL,
  `product_enable` int DEFAULT NULL,
  `product_created_date` date DEFAULT NULL,
  `product_modified_date` date DEFAULT NULL,
  `brand_id` int DEFAULT NULL,
  `category_id` int DEFAULT NULL,
  PRIMARY KEY (`product_id`),
  KEY `brand_id` (`brand_id`),
  KEY `category_id` (`category_id`),
  CONSTRAINT `products_ibfk_1` FOREIGN KEY (`brand_id`) REFERENCES `brands` (`brand_id`),
  CONSTRAINT `products_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `categories` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reviews`
--

DROP TABLE IF EXISTS `reviews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reviews` (
  `review_id` int NOT NULL,
  `rate` int DEFAULT NULL,
  `comment` text,
  `created_at` datetime DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  `order_id` int DEFAULT NULL,
  PRIMARY KEY (`review_id`),
  KEY `user_id` (`user_id`),
  KEY `product_id` (`product_id`),
  KEY `order_id` (`order_id`),
  CONSTRAINT `reviews_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `reviews_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`),
  CONSTRAINT `reviews_ibfk_3` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reviews`
--

LOCK TABLES `reviews` WRITE;
/*!40000 ALTER TABLE `reviews` DISABLE KEYS */;
/*!40000 ALTER TABLE `reviews` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `role_id` int NOT NULL,
  `role_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shipping_address`
--

DROP TABLE IF EXISTS `shipping_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shipping_address` (
  `shipping_address_id` int NOT NULL,
  `fullname` varchar(50) DEFAULT NULL,
  `phone_number` varchar(10) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `address` text,
  `is_default` tinyint(1) DEFAULT NULL,
  `order_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`shipping_address_id`),
  KEY `order_id` (`order_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `shipping_address_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`),
  CONSTRAINT `shipping_address_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shipping_address`
--

LOCK TABLES `shipping_address` WRITE;
/*!40000 ALTER TABLE `shipping_address` DISABLE KEYS */;
/*!40000 ALTER TABLE `shipping_address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` int NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `user_fullname` varchar(50) DEFAULT NULL,
  `user_phone_number` varchar(10) DEFAULT NULL,
  `user_email` varchar(255) DEFAULT NULL,
  `user_created_date` date DEFAULT NULL,
  `user_modified_date` date DEFAULT NULL,
  `user_isactive` int DEFAULT NULL,
  `user_address` text,
  `login_count` int,
  `role_id` int DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `users_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

insert into roles values
(1, 'ADMIN'),
(2, 'CUSTOMER');

INSERT INTO users 
VALUES
(1, '$2a$10$hXQEzjSmWfPrV33HnOlPn.1C4c0XFdgP39u5qBZ2rWGe.4P5a56s2', 'Nam', 'Nguyễn Văn A', '0912345678', 'nguyenvana@example.com', '2025-05-17', '2025-05-17', true, 'Số 1, Đường Lê Lợi, Hà Nội', 1, 2),
(2, '$2a$10$hXQEzjSmWfPrV33HnOlPn.1C4c0XFdgP39u5qBZ2rWGe.4P5a56s2', 'Nữ', 'Trần Thị B', '0934567890', 'tranthib@example.com', '2025-05-17', '2025-05-17', true, 'Số 10, Đường Trần Phú, TP.HCM', 1, 2),
(3, '$2a$10$hXQEzjSmWfPrV33HnOlPn.1C4c0XFdgP39u5qBZ2rWGe.4P5a56s2', 'Nam', 'Lê Văn C', '0987654321', 'levanc@example.com', '2025-05-17', '2025-05-17', true, 'Số 5, Phố Huế, Hà Nội', 1, 2),
(4, '$2a$10$hXQEzjSmWfPrV33HnOlPn.1C4c0XFdgP39u5qBZ2rWGe.4P5a56s2', 'Nữ', 'Phạm Thị D', '0971234567', 'phamthid@example.com', '2025-05-17', '2025-05-17', true, 'Số 15, Nguyễn Trãi, Đà Nẵng', 1, 2),
(5, '$2a$10$hXQEzjSmWfPrV33HnOlPn.1C4c0XFdgP39u5qBZ2rWGe.4P5a56s2', 'Nam', 'Đặng Văn E', '0962345678', 'dangvane@example.com', '2025-05-17', '2025-05-17', true, '123 Lý Thường Kiệt, Cần Thơ', 1, 2),
(6, '$2a$10$hXQEzjSmWfPrV33HnOlPn.1C4c0XFdgP39u5qBZ2rWGe.4P5a56s2', 'Nữ', 'Võ Thị F', '0943456789', 'vothif@example.com', '2025-05-17', '2025-05-17', true, '45 Trường Chinh, Huế', 1, 2),
(7, '$2a$10$hXQEzjSmWfPrV33HnOlPn.1C4c0XFdgP39u5qBZ2rWGe.4P5a56s2', 'Nam', 'Bùi Văn G', '0939876543', 'buivang@example.com', '2025-05-17', '2025-05-17', true, '78 Nguyễn Huệ, Hà Tĩnh', 1, 2),
(8, '$2a$10$hXQEzjSmWfPrV33HnOlPn.1C4c0XFdgP39u5qBZ2rWGe.4P5a56s2', 'Nữ', 'Hoàng Thị H', '0924567890', 'hoangthih@example.com', '2025-05-17', '2025-05-17', true, '9 Hùng Vương, Quảng Ninh', 1, 2),
(9, '$2a$10$hXQEzjSmWfPrV33HnOlPn.1C4c0XFdgP39u5qBZ2rWGe.4P5a56s2', 'Nam', 'Trịnh Văn I', '0918765432', 'trinhvani@example.com', '2025-05-17', '2025-05-17', true, 'Số 66, Phan Đình Phùng, Nghệ An', 1, 2),
(10, '$2a$10$hXQEzjSmWfPrV33HnOlPn.1C4c0XFdgP39u5qBZ2rWGe.4P5a56s2', 'Nữ', 'Đỗ Thị K', '0901234567', 'dothik@example.com', '2025-05-17', '2025-05-17', true, '20 Hai Bà Trưng, Nam Định', 1, 2),
(11, '$2a$10$hXQEzjSmWfPrV33HnOlPn.1C4c0XFdgP39u5qBZ2rWGe.4P5a56s2', 'Nam', 'Trịnh Văn X', '0918765432', 'trinhvani@example.com', '2025-05-17', '2025-05-17', 0, 'Số 66, Phan Đình Phùng, Nghệ An', 1, 2),
(12, '$2a$10$hXQEzjSmWfPrV33HnOlPn.1C4c0XFdgP39u5qBZ2rWGe.4P5a56s2', 'Nữ', 'Đỗ Thị Z', '0901234567', 'dothik@example.com', '2025-05-17', '2025-05-17', 0, '20 Hai Bà Trưng, Nam Định', 1, 2),
(13, '$2a$10$hXQEzjSmWfPrV33HnOlPn.1C4c0XFdgP39u5qBZ2rWGe.4P5a56s2', 'Nam', 'Nguyễn Văn L', '0901000001', 'nguyenvanl@example.com', '2025-05-17', '2025-05-17', true, '1A Đường Láng, Hà Nội', 1, 2),
(14, '$2a$10$hXQEzjSmWfPrV33HnOlPn.1C4c0XFdgP39u5qBZ2rWGe.4P5a56s2', 'Nữ', 'Trần Thị M', '0901000002', 'tranthim@example.com', '2025-05-17', '2025-05-17', true, '2B Trường Sa, TP.HCM', 1, 2),
(15, '$2a$10$hXQEzjSmWfPrV33HnOlPn.1C4c0XFdgP39u5qBZ2rWGe.4P5a56s2', 'Nam', 'Lê Văn N', '0901000003', 'levann@example.com', '2025-05-17', '2025-05-17', true, '3C Nguyễn Đình Chiểu, Đà Nẵng', 1, 2),
(16, '$2a$10$hXQEzjSmWfPrV33HnOlPn.1C4c0XFdgP39u5qBZ2rWGe.4P5a56s2', 'Nữ', 'Phạm Thị O', '0901000004', 'phamthio@example.com', '2025-05-17', '2025-05-17', true, '4D Hùng Vương, Huế', 1, 2),
(17, '$2a$10$hXQEzjSmWfPrV33HnOlPn.1C4c0XFdgP39u5qBZ2rWGe.4P5a56s2', 'Nam', 'Đặng Văn P', '0901000005', 'dangvanp@example.com', '2025-05-17', '2025-05-17', true, '5E Điện Biên Phủ, Cần Thơ', 1, 2),
(18, '$2a$10$hXQEzjSmWfPrV33HnOlPn.1C4c0XFdgP39u5qBZ2rWGe.4P5a56s2', 'Nữ', 'Võ Thị Q', '0901000006', 'vothiq@example.com', '2025-05-17', '2025-05-17', true, '6F Nguyễn Trãi, Hà Nam', 1, 2),
(19, '$2a$10$hXQEzjSmWfPrV33HnOlPn.1C4c0XFdgP39u5qBZ2rWGe.4P5a56s2', 'Nam', 'Bùi Văn R', '0901000007', 'buivanr@example.com', '2025-05-17', '2025-05-17', true, '7G Trần Hưng Đạo, Bắc Ninh', 1, 2),
(20, '$2a$10$hXQEzjSmWfPrV33HnOlPn.1C4c0XFdgP39u5qBZ2rWGe.4P5a56s2', 'Nữ', 'Hoàng Thị S', '0901000008', 'hoangthis@example.com', '2025-05-17', '2025-05-17', true, '8H Quang Trung, Thái Nguyên', 1, 2),
(21, '$2a$10$hXQEzjSmWfPrV33HnOlPn.1C4c0XFdgP39u5qBZ2rWGe.4P5a56s2', 'Nam', 'Trịnh Văn T', '0901000009', 'trinhvant@example.com', '2025-05-17', '2025-05-17', true, '9I Lê Duẩn, Hải Phòng', 1, 2),
(22, '$2a$10$hXQEzjSmWfPrV33HnOlPn.1C4c0XFdgP39u5qBZ2rWGe.4P5a56s2', 'Nữ', 'Đỗ Thị U', '0901000010', 'dothiu@example.com', '2025-05-17', '2025-05-17', true, '10J Hai Bà Trưng, Thanh Hóa', 1, 2),
(23, '$2a$10$hXQEzjSmWfPrV33HnOlPn.1C4c0XFdgP39u5qBZ2rWGe.4P5a56s2', 'Nam', 'Phan Văn V', '0901000011', 'phanvanv@example.com', '2025-05-17', '2025-05-17', true, '11K Trần Phú, Nam Định', 1, 2),
(24, '$2a$10$hXQEzjSmWfPrV33HnOlPn.1C4c0XFdgP39u5qBZ2rWGe.4P5a56s2', 'Nữ', 'Lý Thị W', '0901000012', 'lythiw@example.com', '2025-05-17', '2025-05-17', true, '12L Phạm Ngũ Lão, Quảng Ninh', 1, 2),
(25, '$2a$10$hXQEzjSmWfPrV33HnOlPn.1C4c0XFdgP39u5qBZ2rWGe.4P5a56s2', 'Nam', 'Vũ Văn X', '0901000013', 'vuvanx@example.com', '2025-05-17', '2025-05-17', true, '13M Bạch Đằng, Nghệ An', 1, 2),
(26, '$2a$10$hXQEzjSmWfPrV33HnOlPn.1C4c0XFdgP39u5qBZ2rWGe.4P5a56s2', 'Nữ', 'Ngô Thị Y', '0901000014', 'ngothiy@example.com', '2025-05-17', '2025-05-17', true, '14N Lý Thường Kiệt, Hà Tĩnh', 1, 2),
(27, '$2a$10$hXQEzjSmWfPrV33HnOlPn.1C4c0XFdgP39u5qBZ2rWGe.4P5a56s2', 'Nam', 'Đỗ Văn Z', '0901000015', 'dovanz@example.com', '2025-05-17', '2025-05-17', true, '15O Trần Hưng Đạo, Huế', 1, 2),
(28, '$2a$10$hXQEzjSmWfPrV33HnOlPn.1C4c0XFdgP39u5qBZ2rWGe.4P5a56s2', 'Nữ', 'Trương Thị AA', '0901000016', 'truongthiaa@example.com', '2025-05-17', '2025-05-17', true, '16P Nguyễn Huệ, Đà Nẵng', 1, 2),
(29, '$2a$10$hXQEzjSmWfPrV33HnOlPn.1C4c0XFdgP39u5qBZ2rWGe.4P5a56s2', 'Nam', 'Phạm Văn BB', '0901000017', 'phamvanbb@example.com', '2025-05-17', '2025-05-17', true, '17Q Điện Biên Phủ, TP.HCM', 1, 2),
(30, '$2a$10$hXQEzjSmWfPrV33HnOlPn.1C4c0XFdgP39u5qBZ2rWGe.4P5a56s2', 'Nữ', 'Lê Thị CC', '0901000018', 'lethicc@example.com', '2025-05-17', '2025-05-17', true, '18R Hùng Vương, Cần Thơ', 1, 2),
(31, '$2a$10$hXQEzjSmWfPrV33HnOlPn.1C4c0XFdgP39u5qBZ2rWGe.4P5a56s2', 'Nam', 'Trần Văn DD', '0901000019', 'tranvandd@example.com', '2025-05-17', '2025-05-17', true, '19S Nguyễn Trãi, Hà Nội', 1, 2),
(32, '$2a$10$hXQEzjSmWfPrV33HnOlPn.1C4c0XFdgP39u5qBZ2rWGe.4P5a56s2', 'Nữ', 'Hoàng Thị EE', '0901000020', 'hoangthiee@example.com', '2025-05-17', '2025-05-17', true, '20T Quang Trung, Hải Phòng', 1, 2),
(33, '$2a$10$hXQEzjSmWfPrV33HnOlPn.1C4c0XFdgP39u5qBZ2rWGe.4P5a56s2', 'Nam', 'Nguyễn Văn FF', '0901000021', 'nguyenvanff@example.com', '2025-05-17', '2025-05-17', true, '21U Trần Phú, Nam Định', 1, 2),
(34, '$2a$10$hXQEzjSmWfPrV33HnOlPn.1C4c0XFdgP39u5qBZ2rWGe.4P5a56s2', 'Nữ', 'Đặng Thị GG', '0901000022', 'dangthigg@example.com', '2025-05-17', '2025-05-17', true, '22V Hai Bà Trưng, Hà Nam', 1, 2),
(35, '$2a$10$hXQEzjSmWfPrV33HnOlPn.1C4c0XFdgP39u5qBZ2rWGe.4P5a56s2', 'Nam', 'Phan Văn HH', '0901000023', 'phanvanhh@example.com', '2025-05-17', '2025-05-17', true, '23W Lê Duẩn, Thái Nguyên', 1, 2),
(36, '$2a$10$hXQEzjSmWfPrV33HnOlPn.1C4c0XFdgP39u5qBZ2rWGe.4P5a56s2', 'Nữ', 'Bùi Thị II', '0901000024', 'buithii@example.com', '2025-05-17', '2025-05-17', true, '24X Nguyễn Đình Chiểu, Quảng Ninh', 1, 2),
(37, '$2a$10$hXQEzjSmWfPrV33HnOlPn.1C4c0XFdgP39u5qBZ2rWGe.4P5a56s2', 'Nam', 'Võ Văn JJ', '0901000025', 'vovanjj@example.com', '2025-05-17', '2025-05-17', true, '25Y Phạm Ngũ Lão, Nghệ An', 1, 2),
(38, '$2a$10$hXQEzjSmWfPrV33HnOlPn.1C4c0XFdgP39u5qBZ2rWGe.4P5a56s2', 'Nữ', 'Lý Thị KK', '0901000026', 'lythikk@example.com', '2025-05-17', '2025-05-17', true, '26Z Lý Thường Kiệt, Hà Tĩnh', 1, 2),
(39, '$2a$10$hXQEzjSmWfPrV33HnOlPn.1C4c0XFdgP39u5qBZ2rWGe.4P5a56s2', 'Nam', 'Trịnh Văn LL', '0901000027', 'trinhvanll@example.com', '2025-05-17', '2025-05-17', true, '27A Trần Hưng Đạo, Huế', 1, 2),
(40, '$2a$10$hXQEzjSmWfPrV33HnOlPn.1C4c0XFdgP39u5qBZ2rWGe.4P5a56s2', 'Nữ', 'Ngô Thị MM', '0901000028', 'ngothimm@example.com', '2025-05-17', '2025-05-17', true, '28B Nguyễn Huệ, Đà Nẵng', 1, 2),
(41, '$2a$10$hXQEzjSmWfPrV33HnOlPn.1C4c0XFdgP39u5qBZ2rWGe.4P5a56s2', 'Nam', 'Đỗ Văn NN', '0901000029', 'dovann@example.com', '2025-05-17', '2025-05-17', true, '29C Điện Biên Phủ, TP.HCM', 1, 2),
(42, '$2a$10$hXQEzjSmWfPrV33HnOlPn.1C4c0XFdgP39u5qBZ2rWGe.4P5a56s2', 'Nữ', 'Phạm Thị OO', '0901000030', 'phamthioo@example.com', '2025-05-17', '2025-05-17', true, '30D Hùng Vương, Cần Thơ', 1, 2);

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-13 15:20:19
