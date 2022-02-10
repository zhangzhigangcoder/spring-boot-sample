#### 1. Eager initialization

		the instance of Singleton Class is created at the time of class loading, 
	this is the easiest method to create a singleton class 
	but it has a drawback that instance is created even though client application might not be using it.
	Also, this method doesn’t provide any options for exception handling.

#### 2.Static initialization

		both eager initialization and static block initialization creates the instance 
	even before it’s being used and that is not the best practice to use
		
#### 3. Lazy initialization

		The above implementation works fine in case of the single-threaded environment 
	but when it comes to multithreaded systems, it can cause issues if multiple threads are inside the if condition at the same time. 
	It will destroy the singleton pattern and both threads will get the different instances of the singleton class. 
	
#### 4. Thread Safe initialization

	 	above implementation works fine and provides thread-safety but it reduces the performance 
	because of the cost associated with the synchronized method, 
	although we need it only for the first few threads who might create the separate instances 

#### 5. Double Check Thread Safe initialization

#### 6. High Performance Double Check Thread Safe initializing
	
		local variable result seems unnecessary. But it’s there to improve performance of our code. 
	In cases where instance is already initialized (most of the time), 
	the volatile field is only accessed once (due to “return result;” instead of “return instance;”). 
	This can improve the method’s overall performance by as much as 25 percent.
