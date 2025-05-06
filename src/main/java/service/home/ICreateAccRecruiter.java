package service.home;

import dto.Client;
import dto.User;

public interface ICreateAccRecruiter {
    boolean registerRecruiter(User user, Client client);
}
