General description
-------------------

This is simple event bus (or publishing/subscribing) package for Java.

Example of usage
----------------

There are some tests  provided, but general idea is:

	public class TestHandler {	
		
		@EventHandler
		public void handleString(String event) {
		   ...
		}
	}
	...
	EventDispatcher disp = new EventDispatcher();
	TestHandler handler = new TestHandler();		
	disp.addHandler(handler);
	disp.publish("Hello");
	
EventHandler methods should be public methods with only one parameter. And type
of this parameter determine type of the event that this method should handle.	