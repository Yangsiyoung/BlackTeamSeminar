package com.ysjleader.weightlog.event;

import com.ysjleader.weightlog.dto.request.SignUpRequestDTO;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class SignUpEvent extends ApplicationEvent {
    private SignUpRequestDTO signUpRequestDTO;
    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public SignUpEvent(Object source) {
        super(source);
    }

    public SignUpEvent(Object source, SignUpRequestDTO signUpRequestDTO) {
        super(source);
        this.signUpRequestDTO = signUpRequestDTO;
    }
}
