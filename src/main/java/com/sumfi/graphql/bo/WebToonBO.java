package com.sumfi.graphql.bo;

import com.codepoetics.protonpack.StreamUtils;
import com.sumfi.graphql.domain.webtoon.WebToon;
import com.sumfi.graphql.enums.WebToonType;
import com.sumfi.graphql.utils.CursorUtil;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class WebToonBO {
    private static final List<WebToon> webtoons;

    static {
        webtoons = Arrays.asList(
                WebToon.builder().id(UUID.randomUUID()).title("여신강림").webToonType(WebToonType.Originals).build(),
                WebToon.builder().id(UUID.randomUUID()).title("스퍼맨").webToonType(WebToonType.Originals).build(),
                WebToon.builder().id(UUID.randomUUID()).title("제로게임").webToonType(WebToonType.Originals).build(),
                WebToon.builder().id(UUID.randomUUID()).title("열렙전사").webToonType(WebToonType.Originals).build(),
                WebToon.builder().id(UUID.randomUUID()).title("신도림").webToonType(WebToonType.Originals).build(),
                WebToon.builder().id(UUID.randomUUID()).title("헬퍼").webToonType(WebToonType.Originals).build(),
                WebToon.builder().id(UUID.randomUUID()).title("귀곡의 문").webToonType(WebToonType.Originals).build(),
                WebToon.builder().id(UUID.randomUUID()).title("은주의 방").webToonType(WebToonType.Originals).build(),
                WebToon.builder().id(UUID.randomUUID()).title("엔딩 후 서브남을 주웠다").webToonType(WebToonType.Originals).build()
        );
    }

    public WebToon getWebToon(UUID id) {
        return WebToon.builder()
                .id(id)
                .title("여신강림")
                .webToonType(WebToonType.Originals)
                .build();
    }

    public List<WebToon> getWebtoons(int first, String after) {

        return Optional.ofNullable(after)
                .map(CursorUtil::decodeFrom)
                .map(uuid -> getWebtoonsAfter(first, uuid))
                .orElseGet(() -> getWebtoons(first));
    }

    private List<WebToon> getWebtoons(int first) {
        return webtoons.stream()
                .limit(first)
                .collect(Collectors.toList());
    }

    private List<WebToon> getWebtoonsAfter(int first, UUID id) {
        return StreamUtils
                .skipWhile(webtoons.stream(), webtoon -> !webtoon.getId().equals(id))
                .skip(1)
                .limit(first)
                .collect(Collectors.toList());
    }
}
