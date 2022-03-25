package main

import "fmt"
import "strings"

func makeKey(phrase string) []string {
	var temp string
	var key []string
	for len(temp) < 27 {
		temp += phrase
	}
	temp = temp[0:26]
	key = strings.Split(temp,"")

	return key
}

func makeAlphabet(charMap [26]string) [26][26]string {
	var alphabet [26][26]string

	for i := 0; i < 26; i++ {
		for j := 0; j < 26; j++ {
			index := j + i
			if index > 25 {
				index = i + j - 26
			}
			alphabet[i][j] = charMap[index]
		}	
	}

	return alphabet
}

func findIndex(x string, y string, alphabet [26][26]string) string{
	var hor, ver int
	for i := 0; i < 26; i++ {
		if x == alphabet[0][i] {
			hor = i
		}
		if y == alphabet[i][0] {
			ver = i
			break
		}
	}

	return alphabet[hor][ver]
}

func cipher(message []string, alphabet [26][26]string, key []string) string {
	var encrypted string
	for i := 0; i < len(message); i++ {
		encrypted += findIndex(key[i],message[i], alphabet)
	}
	return encrypted
}

func main(){
	fmt.Println("Vigenere cipher")
	var msg, phrase, encryption string
	var key []string
	var alphabet [26][26]string
	var charMap = [26]string {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"}

	fmt.Print("Ingrese frase para la clave: ")
	fmt.Scan(&phrase)

	alphabet =  makeAlphabet(charMap)
	key = makeKey(phrase)


	fmt.Print("Ingrese mensaje a enriptar: ")
	fmt.Scan(&msg)
	message := strings.Split(msg, "")

	encryption = cipher(message,alphabet,key)
	fmt.Println("Mensaje encriptado:",encryption)
	//fmt.Println(alphabet)
}