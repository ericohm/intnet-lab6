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

Roles, policies, procedures for `digital certificates`. The PKI creates certificates which map public keys to entities and securely stores these. `RA` = Registration Authority. `CA`= Certificate Authority. `VA` = Validation Authority.

_CREATING A CERTIFICATE_

(1) User applies for certificate with his public key at RA. (2) RA confirms the user's identity to the CA. (3) CA issues the certificate. (4) The user can digitally sign a contract using the certificate. (5) The user's identity is checked with a VA which receives information about certificates by CA.

_IN A BROWSER_

Your web browser downloads the web server's certificate, which contains the public key of the web server. This certificate is signed with the private key of a trusted CA.

Your web browser comes installed with the public keys of all of the major CA's. It uses this public key to verify that the web server's certificate was indeed signed by the trusted CA.

The certificate contains the domain name and/or ip address of the web server. Your web browser confirms that the address listed in the certificate is the one to which it has an open connection.

Your web browser generates a `shared symmetric key` which will be used to encrypt the HTTP traffic on this connection; this is much more efficient than using public/private key encryption for everything. Your browser encrypts the symmetric key with the public key of the web server then sends it back, thus ensuring that only the web server can decrypt it, since only the web server has its private key.

Note that the CA is essential to preventing man-in-the-middle attacks. However, even an unsigned certificate will prevent someone from passively listening in on your encrypted traffic, since they have no way to gain access to your shared symmetric key.


**Describe the contents of a certificate**

_Serial Number:_ Used to uniquely identify the certificate.
_Subject:_ The person, or entity identified.
_Signature Algorithm:_ The algorithm used to create the signature.
_Signature:_ The actual signature to verify that it came from the issuer.
_Issuer:_ The entity that verified the information and issued the certificate.
_Valid-From:_ The date the certificate is first valid from.
_Valid-To:_ The expiration date.
_Key-Usage:_ Purpose of the public key (e.g. encipherment, signature, certificate signing...).
_Public Key:_ The public key.
_Thumbprint Algorithm:_ The algorithm used to hash the public key certificate.
_Thumbprint (also known as fingerprint):_ The hash itself, used as an abbreviated form of the public key certificate.

**Explain how a certificate is validated from a technical standpoint, what type of algorithms are used?**

RSA and a hash algorithm.

**How are root certificates delivered to your computer?**

Operating systems and browsers maintain lists of trusted CA root certificates so they can easily verify certificates that the CAs have issued and signed.

**What is a trust chain?**
In order to be sure that the certificate that the server sent is authentic, the certificate needs to be signed cryptographically signed by somebody else's private key in such a way that the signature can be verified by anybody who has the corresponding public key. This third party that signs the certificate are usually certificate authorities. But this last step is harder then it sounds, when the CA and server does the exchange, it's vurneble to  man-in-the-middle attack. We could solve this by adding another certificate and a third key pair, but this could go on and on. To solve this, we include a certificate in this chain that is self-signed. So every trust chain has a root, which signs and confirms itself. 


**Is it safe to store the certificate password in the code? Why/Why not? Explain the handshake.**

Nope.

**When is symmetric encryption used?**

Encrypting data.

**When is asymmetric encryption used?**

Public/private keys. Authentication.


##Keywords
`X.509`: important standard for a PKI to manage certificates and public-key encryption. A key part of the TLS used to secure web and email communication. X.509 specifies formats for public key certificates, certificate revocation lists, attribute certificates, and a certification path validation algorithm.

`TLS/SSL`: Secure Sockets Layer and Transport Layer Secuity (small differences).

`RSA`:

`Private key`:

`Public key`:

`Cipher suite`: combination of authentication, encryption, MAC and key exchange algorithms

`HTTPS`: You specify that you want to connect to a server using SSL by replacing http with https.

`MD5/SHA`: Cryptographic hash functions.

`AES`: Advanced Encryption Standard. A cipher.


