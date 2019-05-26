package com.abril.calls;

import com.nexmo.client.NexmoClient;
import com.nexmo.client.NexmoClientException;
import com.nexmo.client.voice.Call;
import com.nexmo.client.voice.CallEvent;
import com.nexmo.client.voice.VoiceName;
import com.nexmo.client.voice.ncco.Ncco;
import com.nexmo.client.voice.ncco.TalkAction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class CallsApplication {

	public static void main(String[] args) throws IOException, NexmoClientException {
		SpringApplication.run(CallsApplication.class, args);

		String key = System.getProperty("user.dir") +
				"/src/main/java/com/abril/calls/key/private.key";

		NexmoClient client = NexmoClient.builder()
				.applicationId("73a045ee-8d8a-4104-af9d-9a6d1ff79ca0")
				.privateKeyPath(key)
				.build();

		Ncco ncco = new Ncco(
				TalkAction
						.builder("This is a text to speech call from Nexmo")
						.voiceName(VoiceName.SALLI)
						.build()
		);

		String TO_NUMBER = "5216672142394";
		String FROM_NUMBER = "5216672676345";

		CallEvent result = client
				.getVoiceClient()
				.createCall(new Call(TO_NUMBER, FROM_NUMBER, ncco));

		System.out.println(result.getConversationUuid());
	}

}
