package com.github.cato447.main;

import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;
import com.github.cato447.handlers.*;


public class HelloWorldStreamHandler extends SkillStreamHandler {

    private static Skill getSkill(){
       return Skills.standard()
               .addRequestHandlers(
                       new CancelAndStopIntentHandler(),
                       new HelloWorldIntentHandler(),
                       new HelpIntentHandler(),
                       new LaunchRequestHandler(),
                       new SessionEndedRequestHandler())
               .withSkillId("Your Amazon ID")
               .build();
   }

    public HelloWorldStreamHandler(Skill skill) {
        super(skill);
    }

}
