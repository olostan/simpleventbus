package simpleeventbus.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import simpleeventbus.EventDispatcher;
import simpleeventbus.EventHandler;


public class EventDispatcherTest {
	public class CustomEvent {
		private String data;

		public CustomEvent(String data) {
			super();
			this.data = data;
		}

		public String getData() {
			return data;
		}

		
	}
	public class TestHandler {
		String[] expecting;
		public int expectationNo = 0;
		
		public TestHandler(String ... expecting) {
			super();
			this.expecting = expecting;
		}
		
		
		@EventHandler
		public void handleString(String event) {
			assertEquals(expecting[expectationNo], event);
			expectationNo++;		
		}
		@EventHandler
		public void handleCustom(CustomEvent event) {
			assertEquals(expecting[expectationNo], event.getData());
			expectationNo++;
		}
	}
	private final String String_A = "Some String A";
	private final String String_B = "Some String B";
	@Test
	public void simpleExample() {
		EventDispatcher disp = new EventDispatcher();

		TestHandler handler = new TestHandler(String_A);		
		disp.addHandler(handler);
		disp.publish(String_A);
		assertEquals(1,handler.expectationNo);
	}
	@Test 
	public void twoEvents() {
		EventDispatcher disp = new EventDispatcher();
		TestHandler handler = new TestHandler(String_A, String_B);		
		disp.addHandler(handler);
		disp.publish(String_A);
		disp.publish(String_B);
		assertEquals(2,handler.expectationNo);
	}
	@Test 
	public void twoHandlers() {
		EventDispatcher disp = new EventDispatcher();
		TestHandler handler1 = new TestHandler(String_A, String_B);		
		TestHandler handler2 = new TestHandler(String_A, String_B);
		disp.addHandler(handler1);
		disp.addHandler(handler2);
		disp.publish(String_A);
		disp.publish(String_B);
		assertEquals(2,handler1.expectationNo);
		assertEquals(2,handler2.expectationNo);
	}
	@Test
	public void customEvent() {
		EventDispatcher disp = new EventDispatcher();
		TestHandler handler = new TestHandler(String_A);		
		disp.addHandler(handler);
		disp.publish(new CustomEvent(String_A));
		assertEquals(1,handler.expectationNo);
	}
}
