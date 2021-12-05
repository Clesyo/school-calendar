package br.com.schoolcalendar.interfaces;

import br.com.schoolcalendar.forms.NoticeForm;
import br.com.schoolcalendar.models.Notice;

public interface INoticeService {

	Notice save(NoticeForm form);
}
