package com.design.jdk.event;

import java.util.EventListener;

public interface HomeworkListener extends EventListener {
	void publish(HomeworkEventObject event,String homework);
}
