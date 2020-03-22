package com.core.proxy.cglib;

public class OrderService {
	
	public String add(String orderNo)  {
		System.out.println("add a order[orderNo=" + orderNo + "]");
		return "success";
	}
	public static void main(String[] args) {
		byte[] b = {-54, -2, -70, -66, 0, 0, 0, 52, 0, 100, 1, 0, 68, 110, 101, 116, 47, 115, 102, 47, 99, 103, 108, 105, 98, 47, 112, 114, 111, 120, 121, 47, 69, 110, 104, 97, 110, 99, 101, 114, 36, 69, 110, 104, 97, 110, 99, 101, 114, 75, 101, 121, 36, 36, 75, 101, 121, 70, 97, 99, 116, 111, 114, 121, 66, 121, 67, 71, 76, 73, 66, 36, 36, 55, 102, 98, 50, 52, 100, 55, 50, 7, 0, 1, 1, 0, 28, 110, 101, 116, 47, 115, 102, 47, 99, 103, 108, 105, 98, 47, 99, 111, 114, 101, 47, 75, 101, 121, 70, 97, 99, 116, 111, 114, 121, 7, 0, 3, 1, 0, 39, 110, 101, 116, 47, 115, 102, 47, 99, 103, 108, 105, 98, 47, 112, 114, 111, 120, 121, 47, 69, 110, 104, 97, 110, 99, 101, 114, 36, 69, 110, 104, 97, 110, 99, 101, 114, 75, 101, 121, 7, 0, 5, 1, 0, 11, 60, 103, 101, 110, 101, 114, 97, 116, 101, 100, 62, 1, 0, 6, 60, 105, 110, 105, 116, 62, 1, 0, 3, 40, 41, 86, 12, 0, 8, 0, 9, 10, 0, 4, 0, 10, 1, 0, 11, 110, 101, 119, 73, 110, 115, 116, 97, 110, 99, 101, 1, 0, -124, 40, 76, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 83, 116, 114, 105, 110, 103, 59, 91, 76, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 83, 116, 114, 105, 110, 103, 59, 76, 110, 101, 116, 47, 115, 102, 47, 99, 103, 108, 105, 98, 47, 99, 111, 114, 101, 47, 87, 101, 97, 107, 67, 97, 99, 104, 101, 75, 101, 121, 59, 91, 76, 111, 114, 103, 47, 111, 98, 106, 101, 99, 116, 119, 101, 98, 47, 97, 115, 109, 47, 84, 121, 112, 101, 59, 90, 90, 76, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 76, 111, 110, 103, 59, 41, 76, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 79, 98, 106, 101, 99, 116, 59, 1, 0, 115, 40, 76, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 83, 116, 114, 105, 110, 103, 59, 91, 76, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 83, 116, 114, 105, 110, 103, 59, 76, 110, 101, 116, 47, 115, 102, 47, 99, 103, 108, 105, 98, 47, 99, 111, 114, 101, 47, 87, 101, 97, 107, 67, 97, 99, 104, 101, 75, 101, 121, 59, 91, 76, 111, 114, 103, 47, 111, 98, 106, 101, 99, 116, 119, 101, 98, 47, 97, 115, 109, 47, 84, 121, 112, 101, 59, 90, 90, 76, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 76, 111, 110, 103, 59, 41, 86, 12, 0, 8, 0, 14, 10, 0, 2, 0, 15, 1, 0, 7, 70, 73, 69, 76, 68, 95, 48, 1, 0, 18, 76, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 83, 116, 114, 105, 110, 103, 59, 12, 0, 17, 0, 18, 9, 0, 2, 0, 19, 1, 0, 7, 70, 73, 69, 76, 68, 95, 49, 1, 0, 19, 91, 76, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 83, 116, 114, 105, 110, 103, 59, 12, 0, 21, 0, 22, 9, 0, 2, 0, 23, 1, 0, 7, 70, 73, 69, 76, 68, 95, 50, 1, 0, 32, 76, 110, 101, 116, 47, 115, 102, 47, 99, 103, 108, 105, 98, 47, 99, 111, 114, 101, 47, 87, 101, 97, 107, 67, 97, 99, 104, 101, 75, 101, 121, 59, 12, 0, 25, 0, 26, 9, 0, 2, 0, 27, 1, 0, 7, 70, 73, 69, 76, 68, 95, 51, 1, 0, 25, 91, 76, 111, 114, 103, 47, 111, 98, 106, 101, 99, 116, 119, 101, 98, 47, 97, 115, 109, 47, 84, 121, 112, 101, 59, 12, 0, 29, 0, 30, 9, 0, 2, 0, 31, 1, 0, 7, 70, 73, 69, 76, 68, 95, 52, 1, 0, 1, 90, 12, 0, 33, 0, 34, 9, 0, 2, 0, 35, 1, 0, 7, 70, 73, 69, 76, 68, 95, 53, 12, 0, 37, 0, 34, 9, 0, 2, 0, 38, 1, 0, 7, 70, 73, 69, 76, 68, 95, 54, 1, 0, 16, 76, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 76, 111, 110, 103, 59, 12, 0, 40, 0, 41, 9, 0, 2, 0, 42, 1, 0, 8, 104, 97, 115, 104, 67, 111, 100, 101, 1, 0, 3, 40, 41, 73, 3, 0, 9, -55, 91, 1, 0, 16, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 79, 98, 106, 101, 99, 116, 7, 0, 47, 12, 0, 44, 0, 45, 10, 0, 48, 0, 49, 1, 0, 16, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 83, 116, 114, 105, 110, 103, 7, 0, 51, 7, 0, 22, 1, 0, 30, 110, 101, 116, 47, 115, 102, 47, 99, 103, 108, 105, 98, 47, 99, 111, 114, 101, 47, 87, 101, 97, 107, 67, 97, 99, 104, 101, 75, 101, 121, 7, 0, 54, 7, 0, 30, 1, 0, 22, 111, 114, 103, 47, 111, 98, 106, 101, 99, 116, 119, 101, 98, 47, 97, 115, 109, 47, 84, 121, 112, 101, 7, 0, 57, 1, 0, 14, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 76, 111, 110, 103, 7, 0, 59, 1, 0, 6, 101, 113, 117, 97, 108, 115, 1, 0, 21, 40, 76, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 79, 98, 106, 101, 99, 116, 59, 41, 90, 12, 0, 61, 0, 62, 10, 0, 48, 0, 63, 1, 0, 8, 116, 111, 83, 116, 114, 105, 110, 103, 1, 0, 20, 40, 41, 76, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 83, 116, 114, 105, 110, 103, 59, 1, 0, 22, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 83, 116, 114, 105, 110, 103, 66, 117, 102, 102, 101, 114, 7, 0, 67, 10, 0, 68, 0, 10, 12, 0, 65, 0, 66, 10, 0, 48, 0, 70, 1, 0, 6, 97, 112, 112, 101, 110, 100, 1, 0, 44, 40, 76, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 83, 116, 114, 105, 110, 103, 59, 41, 76, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 83, 116, 114, 105, 110, 103, 66, 117, 102, 102, 101, 114, 59, 12, 0, 72, 0, 73, 10, 0, 68, 0, 74, 1, 0, 4, 110, 117, 108, 108, 8, 0, 76, 1, 0, 2, 44, 32, 8, 0, 78, 1, 0, 1, 123, 8, 0, 80, 1, 0, 6, 108, 101, 110, 103, 116, 104, 12, 0, 82, 0, 45, 10, 0, 68, 0, 83, 1, 0, 9, 115, 101, 116, 76, 101, 110, 103, 116, 104, 1, 0, 4, 40, 73, 41, 86, 12, 0, 85, 0, 86, 10, 0, 68, 0, 87, 1, 0, 1, 125, 8, 0, 89, 1, 0, 27, 40, 90, 41, 76, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 83, 116, 114, 105, 110, 103, 66, 117, 102, 102, 101, 114, 59, 12, 0, 72, 0, 91, 10, 0, 68, 0, 92, 10, 0, 68, 0, 70, 1, 0, 19, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 84, 104, 114, 111, 119, 97, 98, 108, 101, 7, 0, 95, 1, 0, 4, 67, 111, 100, 101, 1, 0, 13, 83, 116, 97, 99, 107, 77, 97, 112, 84, 97, 98, 108, 101, 1, 0, 10, 83, 111, 117, 114, 99, 101, 70, 105, 108, 101, 0, 1, 0, 2, 0, 4, 0, 1, 0, 6, 0, 7, 0, 18, 0, 17, 0, 18, 0, 0, 0, 18, 0, 21, 0, 22, 0, 0, 0, 18, 0, 25, 0, 26, 0, 0, 0, 18, 0, 29, 0, 30, 0, 0, 0, 18, 0, 33, 0, 34, 0, 0, 0, 18, 0, 37, 0, 34, 0, 0, 0, 18, 0, 40, 0, 41, 0, 0, 0, 6, 0, 1, 0, 8, 0, 9, 0, 1, 0, 97, 0, 0, 0, 17, 0, 1, 0, 1, 0, 0, 0, 5, 42, -73, 0, 11, -79, 0, 0, 0, 0, 0, 1, 0, 12, 0, 13, 0, 1, 0, 97, 0, 0, 0, 31, 0, 9, 0, 8, 0, 0, 0, 19, -69, 0, 2, 89, 43, 44, 45, 25, 4, 21, 5, 21, 6, 25, 7, -73, 0, 16, -80, 0, 0, 0, 0, 0, 1, 0, 8, 0, 14, 0, 1, 0, 97, 0, 0, 0, 57, 0, 3, 0, 8, 0, 0, 0, 45, 42, -73, 0, 11, 42, 89, 43, -75, 0, 20, 89, 44, -75, 0, 24, 89, 45, -75, 0, 28, 89, 25, 4, -75, 0, 32, 89, 21, 5, -75, 0, 36, 89, 21, 6, -75, 0, 39, 89, 25, 7, -75, 0, 43, -79, 0, 0, 0, 0, 0, 1, 0, 44, 0, 45, 0, 1, 0, 97, 0, 0, 1, -81, 0, 3, 0, 5, 0, 0, 0, -63, 17, 75, 89, 42, -76, 0, 20, 95, 18, 46, 104, 95, 89, -58, 0, 9, -74, 0, 50, -89, 0, 5, 87, 3, 96, 42, -76, 0, 24, 89, -58, 0, 42, 76, 3, 61, -89, 0, 27, 43, 28, 50, 95, 18, 46, 104, 95, 89, -58, 0, 9, -74, 0, 50, -89, 0, 5, 87, 3, 96, -124, 2, 1, 28, 43, -66, -95, -1, -27, -89, 0, 4, 87, 42, -76, 0, 28, 95, 18, 46, 104, 95, 89, -58, 0, 9, -74, 0, 50, -89, 0, 5, 87, 3, 96, 42, -76, 0, 32, 89, -58, 0, 45, 78, 3, 54, 4, -89, 0, 28, 45, 21, 4, 50, 95, 18, 46, 104, 95, 89, -58, 0, 9, -74, 0, 50, -89, 0, 5, 87, 3, 96, -124, 4, 1, 21, 4, 45, -66, -95, -1, -29, -89, 0, 4, 87, 42, -76, 0, 36, 95, 18, 46, 104, 95, 4, -126, 96, 42, -76, 0, 39, 95, 18, 46, 104, 95, 4, -126, 96, 42, -76, 0, 43, 95, 18, 46, 104, 95, 89, -58, 0, 9, -74, 0, 50, -89, 0, 5, 87, 3, 96, -84, 0, 0, 0, 1, 0, 98, 0, 0, 0, -36, 0, 18, -1, 0, 22, 0, 1, 7, 0, 2, 0, 2, 1, 7, 0, 52, -1, 0, 1, 0, 1, 7, 0, 2, 0, 2, 1, 1, -1, 0, 14, 0, 3, 7, 0, 2, 7, 0, 53, 1, 0, 1, 1, -1, 0, 17, 0, 3, 7, 0, 2, 7, 0, 53, 1, 0, 2, 1, 7, 0, 52, -1, 0, 1, 0, 3, 7, 0, 2, 7, 0, 53, 1, 0, 2, 1, 1, 67, 1, -1, 0, 8, 0, 1, 7, 0, 2, 0, 2, 1, 7, 0, 53, 64, 1, -1, 0, 18, 0, 1, 7, 0, 2, 0, 2, 1, 7, 0, 55, -1, 0, 1, 0, 1, 7, 0, 2, 0, 2, 1, 1, -1, 0, 15, 0, 5, 7, 0, 2, 0, 0, 7, 0, 56, 1, 0, 1, 1, -1, 0, 18, 0, 5, 7, 0, 2, 0, 0, 7, 0, 56, 1, 0, 2, 1, 7, 0, 58, -1, 0, 1, 0, 5, 7, 0, 2, 0, 0, 7, 0, 56, 1, 0, 2, 1, 1, 67, 1, -1, 0, 9, 0, 1, 7, 0, 2, 0, 2, 1, 7, 0, 56, 64, 1, -1, 0, 42, 0, 1, 7, 0, 2, 0, 2, 1, 7, 0, 60, -1, 0, 1, 0, 1, 7, 0, 2, 0, 2, 1, 1, 0, 1, 0, 61, 0, 62, 0, 1, 0, 97, 0, 0, 3, -88, 0, 4, 0, 8, 0, 0, 1, 94, 43, -63, 0, 2, -103, 1, 88, 42, -76, 0, 20, 43, -64, 0, 2, -76, 0, 20, 92, -57, 0, 10, -57, 0, 13, 88, -89, 0, 19, -58, 0, 6, -89, 0, 7, 88, -89, 1, 56, -74, 0, 64, -103, 1, 50, 42, -76, 0, 24, 43, -64, 0, 2, -76, 0, 24, 92, -57, 0, 10, -57, 0, 13, 88, -89, 0, 77, -58, 0, 6, -89, 0, 7, 88, -89, 1, 18, 92, -66, 95, -66, -97, 0, 7, 88, -89, 1, 7, 77, 78, 3, 54, 4, -89, 0, 41, 44, 21, 4, 50, 45, 21, 4, 50, 92, -57, 0, 10, -57, 0, 13, 88, -89, 0, 19, -58, 0, 6, -89, 0, 7, 88, -89, 0, -30, -74, 0, 64, -103, 0, -36, -124, 4, 1, 21, 4, 44, -66, -95, -1, -42, 42, -76, 0, 28, 43, -64, 0, 2, -76, 0, 28, 92, -57, 0, 10, -57, 0, 13, 88, -89, 0, 19, -58, 0, 6, -89, 0, 7, 88, -89, 0, -78, -74, 0, 64, -103, 0, -84, 42, -76, 0, 32, 43, -64, 0, 2, -76, 0, 32, 92, -57, 0, 10, -57, 0, 13, 88, -89, 0, 82, -58, 0, 6, -89, 0, 7, 88, -89, 0, -116, 92, -66, 95, -66, -97, 0, 7, 88, -89, 0, -127, 58, 5, 58, 6, 3, 54, 7, -89, 0, 43, 25, 5, 21, 7, 50, 25, 6, 21, 7, 50, 92, -57, 0, 10, -57, 0, 13, 88, -89, 0, 19, -58, 0, 6, -89, 0, 7, 88, -89, 0, 88, -74, 0, 64, -103, 0, 82, -124, 7, 1, 21, 7, 25, 5, -66, -95, -1, -45, 42, -76, 0, 36, 43, -64, 0, 2, -76, 0, 36, -96, 0, 57, 42, -76, 0, 39, 43, -64, 0, 2, -76, 0, 39, -96, 0, 43, 42, -76, 0, 43, 43, -64, 0, 2, -76, 0, 43, 92, -57, 0, 10, -57, 0, 13, 88, -89, 0, 19, -58, 0, 6, -89, 0, 7, 88, -89, 0, 11, -74, 0, 64, -103, 0, 5, 4, -84, 3, -84, 0, 0, 0, 1, 0, 98, 0, 0, 2, 56, 0, 35, -1, 0, 29, 0, 2, 7, 0, 2, 7, 0, 48, 0, 3, 7, 0, 52, 7, 0, 52, 7, 0, 52, -1, 0, 5, 0, 2, 7, 0, 2, 7, 0, 48, 0, 2, 7, 0, 52, 7, 0, 52, -1, 0, 3, 0, 2, 7, 0, 2, 7, 0, 48, 0, 2, 7, 0, 52, 7, 0, 52, 5, -1, 0, 21, 0, 2, 7, 0, 2, 7, 0, 48, 0, 3, 7, 0, 53, 7, 0, 53, 7, 0, 53, -1, 0, 5, 0, 2, 7, 0, 2, 7, 0, 48, 0, 2, 7, 0, 53, 7, 0, 53, -1, 0, 3, 0, 2, 7, 0, 2, 7, 0, 48, 0, 2, 7, 0, 53, 7, 0, 53, -1, 0, 10, 0, 2, 7, 0, 2, 7, 0, 48, 0, 2, 7, 0, 53, 7, 0, 53, -2, 0, 7, 7, 0, 53, 7, 0, 53, 1, -1, 0, 18, 0, 5, 7, 0, 2, 7, 0, 48, 7, 0, 53, 7, 0, 53, 1, 0, 3, 7, 0, 52, 7, 0, 52, 7, 0, 52, -1, 0, 5, 0, 5, 7, 0, 2, 7, 0, 48, 7, 0, 53, 7, 0, 53, 1, 0, 2, 7, 0, 52, 7, 0, 52, -1, 0, 3, 0, 5, 7, 0, 2, 7, 0, 48, 7, 0, 53, 7, 0, 53, 1, 0, 2, 7, 0, 52, 7, 0, 52, 5, 2, -8, 0, 6, -1, 0, 21, 0, 2, 7, 0, 2, 7, 0, 48, 0};
		System.out.println(new String(b));
	}
}