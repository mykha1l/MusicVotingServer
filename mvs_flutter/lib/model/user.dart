class UserResponse {
  String? password;
  String? username;
  String? nationality;
  int? age;
  String? genre;
  bool? enabled;
  String? authority;

  UserResponse({
    this.password,
    this.username,
    this.nationality,
    this.genre,
    this.age,
    this.enabled,
    this.authority,
  });

  //Below function is for fetching object from Json
  factory UserResponse.fromJson(Map<String, dynamic> json) => UserResponse(
        password: json["password"] == null ? null : json["password"],
        username: json["username"] == null ? null : json["username"],
        nationality: json["nationality"] == null ? null : json["nationality"],
        genre: json["genre"] == null ? null : json["genre"],
        age: json["age"] == null ? null : json["age"],
        enabled: json["enabled"] == null ? null : json["enabled"],
        authority: json["authority"] == null ? null : json["authority"],
      );

  Map<String, dynamic> toJson() => {
        "password": password,
        "username": username,
        "nationality": nationality,
        "genre": genre,
        "age": age,
        "enabled": enabled,
        "authority": authority,
      };

  String toString() => 'LoginResponse{username: $username, '
      'password: $password, age: $age, nationality: $nationality, '
      'enabled: $enabled, authority: $authority}';
}
