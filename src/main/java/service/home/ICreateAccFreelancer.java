package service.home;

import dto.Freelancer;
import dto.User;

public interface ICreateAccFreelancer {
    boolean registerFreelancer(User user, Freelancer freelancer);
}
