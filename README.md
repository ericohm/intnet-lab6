# intnet-lab6
Lab 6 in DD2390 at KTH.

## Connecting to the server
1. Visit `https://localhost:1234`
2. Accept the certificate

## Generating keystore, truststore and certificate
```
keytool -genkey -keyalg "RSA" -storepass ninaeric -validity 365 -alias lab6 -keystore lab6keystore
keytool -export -alias lab6 -storepass ninaeric -file lab6.cer -keystore lab6keystore
keytool -import -file lab6.cer -alias lab6 -keystore lab6truststore
```

## Listing the keystore
`keytool -list -storepass ninaeric -keystore lab6keystore`

## Questions

**Explain how the PKI (Public Key Infrastructure) work.**

Roles, policies, procedures for `digital certificates`. The PKI creates certificates which map public keys to entities and securely stores these certificates in a central repository.
`RA` = Registration Authority. `CA`= Certificate Authority. `VA` = Validation Authority.

(1) User applies for certificate with his public key at RA. (2) RA confirms the user's identity to the CA. (3) CA issues the certificate. (4) The user can digitally sign a contract using the certificate. (5) The user's identity is checked with a VA which receives information about certificates by CA.

Here is a very simplified explanation:

Your web browser downloads the web server's certificate, which contains the public key of the web server. This certificate is signed with the private key of a trusted certificate authority.

Your web browser comes installed with the public keys of all of the major certificate authorities. It uses this public key to verify that the web server's certificate was indeed signed by the trusted certificate authority.

The certificate contains the domain name and/or ip address of the web server. Your web browser confirms that the address listed in the certificate is the one to which it has an open connection.

Your web browser generates a shared symmetric key which will be used to encrypt the HTTP traffic on this connection; this is much more efficient than using public/private key encryption for everything. Your browser encrypts the symmetric key with the public key of the web server then sends it back, thus ensuring that only the web server can decrypt it, since only the web server has its private key.

Note that the certificate authority (CA) is essential to preventing man-in-the-middle attacks. However, even an unsigned certificate will prevent someone from passively listening in on your encrypted traffic, since they have no way to gain access to your shared symmetric key.


**Describe the contents of a certificate**


**Explain how a certificate is validated from a technical standpoint, what type of algorithms are used?**


**How are root certificates delivered to your computer?**

Operating systems and browsers maintain lists of trusted CA root certificates so they can easily verify certificates that the CAs have issued and signed.

**What is a trust chain?**


**Is it safe to store the certificate password in the code? Why/Why not? Explain the handshake.**


**When is symmetric encryption used?**


**When is asymmetric encryption used?**

##Keywords
`X.509`:

`TLS/SSL`: Secure Sockets Layer and Transport Layer Secuity (small differences).

`RSA`:

`Private key`:

`Public key`:

`Cipher suite`:

`HTTPS`: You specify that you want to connect to a server using SSL by replacing http with https.

`MD5/SHA`: Cryptographic hash functions.

`AES`: Advanced Encryption Standard. A cipher.


