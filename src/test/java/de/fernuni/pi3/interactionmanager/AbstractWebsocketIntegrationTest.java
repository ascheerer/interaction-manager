package de.fernuni.pi3.interactionmanager;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.websocket.WebSocket;
import com.ning.http.client.websocket.WebSocketTextListener;
import com.ning.http.client.websocket.WebSocketUpgradeHandler;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/spring/interaction-manager.xml" })
public abstract class AbstractWebsocketIntegrationTest {

	protected String response;
	protected AsyncHttpClient httpClient;
	protected WebSocket websocket;

	@Before
	public void setUp() throws Exception {
		response = null;
		httpClient =  new AsyncHttpClient();
		websocket  = httpClient.prepareGet(
				"ws://localhost:9292/interaction-manager").execute(new WebSocketUpgradeHandler.Builder().addWebSocketListener(new WebSocketTextListener() {
					
					public void onOpen(WebSocket websocket) {
						// TODO Auto-generated method stub
						
					}
					
					public void onError(Throwable t) {
						// TODO Auto-generated method stub
						
					}
					
					public void onClose(WebSocket websocket) {
						// TODO Auto-generated method stub
						
					}
					
					public void onMessage(String message) {
						response = message;
					}
					
					public void onFragment(String fragment, boolean last) {
						// TODO Auto-generated method stub
						
					}
				}).build()).get();
	}

	@After
	public void tearDown() {
		websocket.close();
		httpClient.close();
	}

	protected void sendInteractionManagerMessage(Event message) throws Exception {
		websocket.sendTextMessage(message.toJson());
		new CountDownLatch(1).await(1, TimeUnit.SECONDS);
	}

}
