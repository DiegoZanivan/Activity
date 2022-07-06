package com.task.converter;

import com.task.data.document.Activity;
import com.task.dto.ActivityDto;
import com.task.data.service.ActivityService;
import org.apache.logging.log4j.util.Strings;
import reactor.core.publisher.Mono;

public class ActivityConverter {

    public Mono<ActivityDto> convertToDto(Activity activity) {
        ActivityDto dto = new ActivityDto();
        dto.setDescription(activity.getDescription());
        dto.setStart(activity.getStart());
        dto.setFinish(activity.getFinish());
        dto.setId(activity.getId());
        return Mono.just(dto);
    }

    public Mono<Activity> convertToDocument(ActivityService service, ActivityDto dto) {
        Mono<Activity> mono;
        if (Strings.isBlank(dto.getId())) {
            mono = Mono.just(new Activity());
        } else {
            mono = Mono.just(dto.getId())
                    .flatMap(service::findById);
        }
        return mono.map(x -> {
            x.setStart(dto.getStart());
            x.setFinish(dto.getFinish());
            x.setDescription(dto.getDescription());
            return x;
        });
    }
}
