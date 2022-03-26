#49276d206b696c6c696e6720796f757220627261696e206c696b65206120706f69736f6e6f7573206d757368726f6f6d

hex = input("Ingrese codigo hexadecimal: ")
hex = int(hex, base=16)
binString = bin(hex)

x = 6
clusters = [binString[y-x:y] for y in range(x, len(binString)+x, x)]
#print(res)

for i in range(len(clusters)):
    clusters[i] = int(clusters[i], 2)

print(clusters)

