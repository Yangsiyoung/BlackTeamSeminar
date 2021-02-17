package com.ysjleader.weightlog.event;

import com.ysjleader.weightlog.dto.SendEMailDTO;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class VerifyEmailEvent extends ApplicationEvent {
    private SendEMailDTO sendEMailDTO;
    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public VerifyEmailEvent(Object source) {
        super(source);
    }

    public VerifyEmailEvent(Object source, SendEMailDTO sendEMailDTO) {
        super(source);
        this.sendEMailDTO = sendEMailDTO;
    }
}
