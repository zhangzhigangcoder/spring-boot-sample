#### 1.适用场景

		Factory design pattern is used when we have a super class with multiple sub-classes and based on input,
	we need to return one of the sub-class. 
	This pattern take out the responsibility of instantiation of a class from client program to the factory class. 
	We can apply Singleton pattern on Factory class or make the factory method static.

#### 2.Advantages

	1) provides abstraction between implementation and client classes through inheritance.
	2) removes the instantiation of actual implementation classes from client code. 
	3) makes our code more robust, less coupled and easy to extend. 
	   For example, we can easily change PC class implementation because client program is unaware of this.
	   
#### 3.Examples in JDK
	1) java.util.Calendar, ResourceBundle and NumberFormat getInstance() methods uses Factory pattern.
	2) valueOf() method in wrapper classes like Boolean, Integer etc.