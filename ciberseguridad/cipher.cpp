#include <iostream>
#include <string.h>
#include <stdlib.h>

void cipher();
void decipher();

/*
    To run this program use a compiler like g++ 
    example (linux): g++ cipher.cpp -o cipher


    App limitations:
        -NO BLANK SPACES
        -No numbers
        -No keys > 25
*/

int main() {

    std::string option;

    system("clear");

    while (option != "3")
    {
        std::cout << "\n -- Select an option -- " << std::endl;
        std::cout << "[1] Cipher" << std::endl;
        std::cout << "[2] Decipher" << std::endl;
        std::cout << "[3] Exit\n" << std::endl;
        std::cout << "==> ";
        std::cin >> option;
        std::cout << "\n";

        if (option == "1") {
            cipher();
        } else if (option == "2") {
            decipher();
        }
    }
    
    return 0;
}

void cipher() {
    char ch, msg[50];
    int key, i;

    std::cout << "Enter message: ";
    std::cin >> msg;
    std::cout << "Enter key: ";
    std::cin >> key;

    for (i = 0; i < strlen(msg); i++) {
        if ((int(msg[i]) - key) < 97) {
            msg[i] = int('z') - abs(((int(msg[i]) - 97) - key)) + 1;
        }
        else {
            msg[i] = int(msg[i]) - key;
        }
    }
    
    std::cout << "\n--------------------------------------";
    std::cout << "\n    Encrypted message: " << msg <<std::endl;
    std::cout << "--------------------------------------\n";
}

void decipher() {
    char ch, msg[50];
    int key, i;

    std::cout << "Enter message: ";
    std::cin >> msg;
    std::cout << "Enter key: ";
    std::cin >> key;

    for (i = 0; i < strlen(msg); i++) {
        if ((int(msg[i]) + key) > 122) {
            msg[i] = int('a') + ((int(msg[i]) - 122) + key) - 1;
        }
        else {
            msg[i] = int(msg[i]) + key;
        }

    }
    
    std::cout << "\n--------------------------------------";
    std::cout << "\n    Dencrypted message: " << msg <<std::endl;
    std::cout << "--------------------------------------\n";
}