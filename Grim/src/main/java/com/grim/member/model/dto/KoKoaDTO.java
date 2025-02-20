package com.grim.member.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KoKoaDTO {
	private Long id;
	private KakaoAccount kakao_account;
	
	@Getter
	@Setter
	public static class KakaoAccount {
		private Profile profile;
		private String email;
		
		@Getter
		@Setter
		public static class Profile{
			private String nickname;
			private String profile_image_url;
			private String thumbnail_image_url;
		}
	}
}
