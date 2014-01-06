package com.froyo;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

public class MyActor extends UntypedActor {

    @Override
    public void preStart() {
        
        // create the actor
        final ActorRef greeter = getContext().actorOf(Props.create(Greeter.class), "greeter");
        
        // Actor tell
        greeter.tell(Greeter.Msg.GREET, getSelf());
    }

    @Override
    public void onReceive(Object msg) {
        
        // Response
        if (msg == Greeter.Msg.DONE) {            
            getContext().stop(getSelf());
        } else
            unhandled(msg);
    }
}